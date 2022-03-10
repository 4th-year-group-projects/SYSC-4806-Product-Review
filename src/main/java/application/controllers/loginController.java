package application.controllers;

import application.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import application.models.User;
import application.repositories.UserRepository;

@Controller
public class loginController {
    @Autowired
    private UserRepository repository;

    public loginController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute User user) {
//        User userFromRepo = this.repository.findByUsername(user.getUsername());
//        if((userFromRepo != null) && userFromRepo.getPassword().equals(user.getPassword())){
//            return "user";
//        }
//        return "loginFailure";
//    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSuccess(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        this.repository.save(user);
        return "user";
    }
}
