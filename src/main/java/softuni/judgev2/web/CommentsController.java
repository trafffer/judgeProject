package softuni.judgev2.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.judgev2.model.binding.CommentsAddBindingModel;
import softuni.judgev2.model.service.CommentServiceModel;
import softuni.judgev2.model.view.HomeworkViewModel;
import softuni.judgev2.services.CommentService;
import softuni.judgev2.services.HomeworkService;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final HomeworkService homeworkService;
    private final ModelMapper mapper;
    private final CommentService commentService;

    public CommentsController(HomeworkService homeworkService, ModelMapper mapper, CommentService commentService) {
        this.homeworkService = homeworkService;
        this.mapper = mapper;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("commentsModel")){
            model.addAttribute("commentsModel",new CommentsAddBindingModel());
        }
        HomeworkViewModel homework = mapper.map(homeworkService.findHomeworkforScoring(),
                HomeworkViewModel.class);
        model.addAttribute("homework",homework);

        return "homework-check";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CommentsAddBindingModel commentsModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentsModel",commentsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentsModel",bindingResult);
            return "redirect:add";
        }
        CommentServiceModel serviceModel = mapper.map(commentsModel, CommentServiceModel.class);
        serviceModel.setTextContent(commentsModel.getDescription());
        commentService.add(serviceModel,commentsModel.getHomeworkId());
        return "redirect:/";
    }
}
