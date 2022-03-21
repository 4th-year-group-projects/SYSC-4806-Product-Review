package application.controllers;

import application.models.Product;
import application.models.Review;
import application.models.User;
import application.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserRepository repository;
    private User loggedInUser;

    public UserController(UserRepository repository) {
        this.repository = repository;
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
        User user3 = new User("Liya", "4321");
        User user4 = new User("Adela", "4321");
        curUser.addFollower(user1);
        curUser.addFollower(user2);
        curUser.addFollower(user3);
        curUser.addFollower(user4);

        Set<User> followers = curUser.getFollowersList();
        if (!followers.isEmpty())
            model.addAttribute("followers", followers);
        return "followList";
    }

    @GetMapping("/followingList/{id}")
    public String followingList(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        User curUser = this.repository.findUserById(id);

        //for testing purposes
        User user1 = new User("Noah", "4321");
        User user2 = new User("Aubin", "4321");
        User user3 = new User("Liya", "4321");
        curUser.followUser(user1);
        curUser.followUser(user2);
        curUser.followUser(user3);

        Set<User> following = curUser.getFollowingList();
        if (!following.isEmpty())
            model.addAttribute("following", following);
        return "followingList";
    }

    @GetMapping("/reviewsWritten/{id}")
    public String reviewsWritten(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        User curUser = this.repository.findUserById(id);

        Product product1 = new Product("Vaccuum", "description", "url");
        curUser.writeReview(product1, 5, "Good product!");
        Product product2 = new Product("TV", "description", "url");
        curUser.writeReview(product2, 1, "Broke within a month!");

        Set<Review> reviewsWritten = curUser.getReviewList();
        model.addAttribute("reviews", reviewsWritten);
        return "reviewsWritten";
    }

    @GetMapping("/viewUsers/{id}")
    public String viewUsers(@PathVariable long id, Model model) {
        this.loggedInUser = this.repository.findUserById(id);
        List<User> users = this.repository.findAll();
        users.remove(loggedInUser);
        // for testing, will be removed
        User user1 = new User("Noah", "4321");
        User user2 = new User("Aubin", "4321");
        User user3 = new User("Liya", "4321");
        users.add(user1);
        users.add(user2);

        model.addAttribute("userslist", users);
        return "viewusers";
    }

    @GetMapping("/followUser/{id}")
    public String followUser(@PathVariable long id, Model model) {
        User followUser = this.repository.findUserById(id);
        followUser.addFollower(loggedInUser);
        return "followUserSucess";
    }

}
