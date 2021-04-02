package softuni.judgev2.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.judgev2.model.binding.ExerciseAddBindingModel;
import softuni.judgev2.model.service.ExerciseServiceModel;
import softuni.judgev2.services.ExerciseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;

    public ExerciseController(ModelMapper modelMapper, ExerciseService exerciseService) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
    }


    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("exerciseAddBindingModel")){
            model.addAttribute("exerciseAddBindingModel",new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ExerciseAddBindingModel model,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
                             ){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel",model);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel"
            ,bindingResult);
            return "redirect:add";
        }
        exerciseService.addEx(modelMapper.map(model, ExerciseServiceModel.class));
        return "redirect:/";
    }
}
