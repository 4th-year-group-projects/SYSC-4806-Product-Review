package application.controllers;

import application.models.Product;
import application.models.Review;
import application.models.User;
import application.repositories.ProductRepository;
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
    ProductRepository productRepository;
    public ReviewController(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }
    @GetMapping("/createreview/{id}")
    public String createProductReviewForm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        Review r = new Review();
        r.setProduct(productRepository.findById(id));
        model.addAttribute("review", r);
        return "createreview";
    }

    @PostMapping("/createreview/{id}")
    public String createProductReviewSuccess(@PathVariable long id, @ModelAttribute Review review, Model model) {
        Product p = this.productRepository.findById(id);
        p.addReview(review);
        model.addAttribute("id", id);
        this.productRepository.save(p);
        return "reviewSuccess";
    }


    @GetMapping("/viewreviews/{id}")
    public String viewReviewsForProduct(@PathVariable long id, Model model) {
        Product currentProduct = this.productRepository.findById(id);
        if (currentProduct == null){
            return "invalidproduct";
        }
        model.addAttribute("currentProduct", currentProduct);
        model.addAttribute("id", currentProduct.getId());
        return "viewreviews";
    }


}
