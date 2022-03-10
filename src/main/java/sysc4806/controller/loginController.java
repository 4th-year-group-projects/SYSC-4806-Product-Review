package sysc4806.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sysc4806.models.User;
import sysc4806.repositories.UserRepository;

@Controller
public class loginController {

    private UserRepository repository;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = this.repository.findByUsername(username);
        String userPassword = user.getPassword();
        if(userPassword.equals(password)){
            return "user";
        }
        return "loginFailure";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        this.repository.save(user);
        return "login";
    }

}
