package application.controllers;

import application.models.User;
import application.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/previousReview")
    public String previousReview(@RequestParam String username, Model model) {
        User a = this.repository.findByUsername(username);
        model.addAttribute("user", a);
        return "viewPreviousReviews";
    }

    @GetMapping("/user")
    public String user(@RequestParam String username, Model model) {

        return "user";
    }
}
