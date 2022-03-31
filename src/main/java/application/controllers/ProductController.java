package application.controllers;

import application.models.Product;
import application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/createproduct")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "createproduct";
    }

    @GetMapping("/filterproduct")
    public String filterProduct(Model model, @RequestParam(value = "categorySelect") String category) {
        System.out.println(category.toString());
        List<Product> list = this.repository.findAll();
        List<Product> newlist = new ArrayList<Product>();
        for ( Product product : list){
            if( product.getCategory().toString().equals(category)){
                newlist.add(product);
            }
        }
        model.addAttribute("productlist", newlist);
        return "viewproducts";
    }

    @PostMapping("/createproduct")
    public String createProductFormSuccess(@ModelAttribute Product product, Model model) {
        model.addAttribute(product);
        this.repository.save(product);
        return "createproductsuccess";
    }

    @GetMapping("/viewproducts")
    public String viewproducts(Model model) {
        List<Product> list = this.repository.findAll();
        model.addAttribute("productlist", list);
        return "viewproducts";
    }


}
