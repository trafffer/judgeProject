package softuni.judgev2.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.judgev2.model.binding.UserLoginBindingModel;
import softuni.judgev2.model.binding.UserRegisterBindingModel;
import softuni.judgev2.model.service.UserServiceModel;
import softuni.judgev2.security.CurrentUser;
import softuni.judgev2.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService service;
    private final CurrentUser user;

    public UserController(ModelMapper modelMapper, UserService service, CurrentUser user) {
        this.modelMapper = modelMapper;
        this.service = service;
        this.user = user;
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){
         if (bindingResult.hasErrors()){
             redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
             redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                     bindingResult);
             return "redirect:login";
         }
         UserServiceModel userServiceModel = service.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(),
                 userLoginBindingModel.getPassword());

         if (userServiceModel==null){
             redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
             redirectAttributes.addFlashAttribute("notFound",true);
             return "redirect:login";
         }

//        httpSession.setAttribute("user",userServiceModel);
        service.login(userServiceModel);
        return "redirect:/";
    }


    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("UserRegisterBindingModel")){
            model.addAttribute("UserRegisterBindingModel",new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm (@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("UserRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterBindingModel",
                    bindingResult);
            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel,UserServiceModel.class);
        service.registerUser(userServiceModel);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(){
        service.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id,Model model){
        if (user.isAnonymous()){
         return "redirect:/users/login";
        }
        model.addAttribute("user",service.findViewModel(id));
        return "profile";
    }
}
