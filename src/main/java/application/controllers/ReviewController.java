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

    public ReviewController(ReviewRepository repository) {
        this.reviewRepository = repository;
    }

    //@RequestMapping(value = "/review", method = RequestMethod.GET)
    @GetMapping("/review")
    public String createProductReviewForm( Model model) {
        model.addAttribute("review", new Review());
        return "review";
    }

    //@RequestMapping(value="/review", method = RequestMethod.POST)
    @PostMapping("/review")
    public String createProductReviewSuccess(@ModelAttribute Review review, Model model) {
        model.addAttribute(review);
        this.reviewRepository.save(review);
        return "reviewSuccess";
    }

    //@RequestMapping(value = "/viewreviews", method = RequestMethod.GET)
    @GetMapping("/viewreviews")
    public String viewReviews(Model model) {
        List<Review> reviews = this.reviewRepository.findAll();
        model.addAttribute("reviewlist", reviews);
        return "viewreviews";
    }
}
