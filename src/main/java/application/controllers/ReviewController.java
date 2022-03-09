package application.controllers;

import application.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository repository) {
        this.reviewRepository = repository;
    }

    @GetMapping("/")
    public String home() {
        return "Hello, World!";
    }

    @GetMapping("/review")
    public String createProductReviewForm() {
        return "review";
    }
}
