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
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/userProfile")
    public String userProfile( Model model) {
       // model.addAttribute("id", id);
        return "userProfile";
    }


    @GetMapping("/followList")
    public String followList(HttpServletRequest request, Model model) throws ServletException, IOException{
        HttpSession session = request.getSession();
        long curUserID = (long)session.getAttribute("userId");
        User curUser = this.repository.findUserById(curUserID);

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

    @GetMapping("/followingList")
    public String followingList(HttpServletRequest request, Model model) throws ServletException, IOException{
        HttpSession session = request.getSession();
        long curUserID = (long)session.getAttribute("userId");
        User curUser = this.repository.findUserById(curUserID);

        //for testing purposes
        User user1 = new User("Noah", "4321");
        User user2 = new User("Aubin", "4321");
        User user3 = new User ("Liya", "4321");
        curUser.followUser(user1);
        curUser.followUser(user2);
        curUser.followUser(user3);

        Set<User> following = curUser.getFollowingList();
        model.addAttribute("following", following);
        return "followingList";
    }

    @GetMapping("/reviewsWritten")
    public String reviewsWritten(HttpServletRequest request, Model model) throws ServletException, IOException{
        HttpSession session = request.getSession();
        long curUserID = (long)session.getAttribute("userId");
        User curUser = this.repository.findUserById(curUserID);

        Product product1 = new Product("Vaccuum", "description", "url");
        curUser.writeReview(product1, 5, "Good product!");
        Product product2 = new Product("TV", "description", "url");
        curUser.writeReview(product2, 1, "Broke within a month!");

        Set<Review> reviewsWritten = curUser.getReviewList();
        model.addAttribute("reviews", reviewsWritten);
        return "reviewsWritten";
    }

    @GetMapping("/mostFollowedUsers")
    public String mostFolowedUsers(HttpServletRequest request, Model model) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long curUserID = (long)session.getAttribute("userId");
        User curUser = this.repository.findUserById(curUserID);

        //for testing purposes
        User user1 = new User("Noah", "4321");
        User user2 = new User("Aubin", "4321");
        User user3 = new User ("Liya", "4321");
        User user4 = new User ("Adela", "4321");

        this.repository.save(user1);
        this.repository.save(user2);
        this.repository.save(user3);
        this.repository.save(user4);
        user1.addFollower(user2);
        user1.addFollower(user3);
        user1.addFollower(user4);
        user2.addFollower(user1);
        user2.addFollower(user3);
        user3.addFollower(user4);


        List<User> users = this.repository.findAll();
        users.remove(curUser);
        ArrayList<String> results = new ArrayList<>();

        for(int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            List<Integer> sortedFollowers = new ArrayList<>();
            Set<User> followerList = user.getFollowersList();
            sortedFollowers.add(followerList.size());
            Collections.sort(sortedFollowers);

            for(int j = 0; j < sortedFollowers.size(); j++) {
                if (user.getFollowersList().size() == sortedFollowers.get(j)) {
                    results.add(user.getUsername());
                }
            }
        }

        model.addAttribute("results", results);

        return "mostFollowedUsers";
    }


}
