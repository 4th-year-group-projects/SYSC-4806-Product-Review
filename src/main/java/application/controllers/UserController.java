package application.controllers;

import application.models.User;
import application.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

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

    @GetMapping("/userProfile/{id}")
    public String userProfile(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "userProfile";
    }

    @GetMapping("/followList/{id}")
    public String followList(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        User curUser = this.repository.findUserById(id);

        //for testing purposes
        User user1 = new User("Noah", "4321");
        User user2 = new User("Aubin", "4321");
        User user3 = new User ("Liya", "4321");
        User user4 = new User ("Adela", "4321");
        curUser.addFollower(user1);
        curUser.addFollower(user2);
        curUser.addFollower(user3);
        curUser.addFollower(user4);

        Set<User> followers = curUser.getFollowersList();
        model.addAttribute("followers", followers);
        return "followList";
    }

    @GetMapping("/followingList/{id}")
    public String followingList(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        User curUser = this.repository.findUserById(id);
        Set<User> following = curUser.getFollowingList();
        model.addAttribute("following", following);
        return "followingList";
    }

    @GetMapping("/reviewsWritten/{id}")
    public String reviewsWritten(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "reviewsWritten";
    }


}
