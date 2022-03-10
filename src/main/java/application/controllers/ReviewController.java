package application.controllers;

import application.models.Product;
import application.models.Review;
import application.models.User;
import application.repositories.ReviewRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewController(ReviewRepository repository, UserRepository userRepository) {
        this.reviewRepository = repository;
        this.userRepository = userRepository;
    }
//
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String home(Model model) {
//        User user = new User();
//        return "home";
//    }

//    @RequestMapping(value = "/home", method = RequestMethod.POST)
//    public String home2(Model model) {
//        User user = new User();
//        model.addAttribute(user);
//        this.userRepository.save(user);
//        model.addAttribute("User", user);
//        return "review";
//    }

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String createProductReviewForm( Model model) {
        //model.addAttribute("id", id);
        //model.addAttribute("review", new Review());
        User user = new User();
        userRepository.save(user);
        model.addAttribute(user);
        return "review";
    }

    @RequestMapping(value="{id}/new", method = RequestMethod.GET)
    public String newReview(@PathVariable String id, Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        return "review";
    }


    @RequestMapping(value="/reviewCreated/{id}", method = RequestMethod.POST)
    public String createProductReviewSuccess(@PathVariable(value = "id", required = false) Long id, @ModelAttribute Review review, Model model) {
//        User user = new User();
//        model.addAttribute(user);
//        this.userRepository.save(user);
//        model.addAttribute("User", user);


      //  model.addAttribute("id", id);
        User user = this.userRepository.findById(id);

        if(user == null) {
            return "reviewFailure";
        }
//        r.setReviewedProduct(review.getReviewedProduct());
//        r.setReviewComment(review.getReviewComment());
//        r.setReviewRating(review.getReviewRating());
        List reviewList = user.getReviews();
        reviewList.add(review);
        this.userRepository.save(user);
        this.reviewRepository.save(review);
        return "reviewSuccess";
    }
}
