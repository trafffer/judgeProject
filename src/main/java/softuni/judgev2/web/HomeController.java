package softuni.judgev2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.judgev2.security.CurrentUser;
import softuni.judgev2.services.CommentService;
import softuni.judgev2.services.ExerciseService;
import softuni.judgev2.services.UserService;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, CommentService commentService, UserService userService) {
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        if (currentUser.isAnonymous()){
            return "index";
        }
        model.addAttribute("exercises", exerciseService.findAllExNames());
        model.addAttribute("avg",commentService.avgScore());
        model.addAttribute("totalStudents",userService.totalCount());
        model.addAttribute("scoreMap",commentService.findScoreMap());
        return "home";
    }




}
