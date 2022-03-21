package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import application.models.User;
import application.repositories.UserRepository;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserRepository repository;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) throws ServletException, IOException {
        User userFromRepo = this.repository.findByUsername(user.getUsername());
        if((userFromRepo != null) && userFromRepo.getPassword().equals(user.getPassword())){
            model.addAttribute("id", userFromRepo.getId());
            HttpSession session = request.getSession();
            session.setAttribute("userId", userFromRepo.getId());
            session.setAttribute("username", user.getUsername());
            return "user";
        }
        return "loginFailure";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSuccess(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        this.repository.save(user);
        return "login";
    }
}
