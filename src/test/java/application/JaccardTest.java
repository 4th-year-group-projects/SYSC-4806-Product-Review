package application;

import application.models.Product;
import application.models.Review;
import application.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class JaccardTest {
    private static final int NUM_USERS = 5;
    private static final int NUM_PRODUCTS = 5;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();

    @BeforeEach
    public void setup() {
        // Instantiate Products
        for(int i = 0; i < NUM_PRODUCTS; i++) {
            products.add(new Product("p"+i, "desc"+i, "url"+i));
        }
        // Instantiate Users
        for(int i = 0; i < NUM_USERS; i++) {
            users.add(new User("user"+i, "pass"+i));
        }
    }

    @Test
    public void testCalcualateSingleJaccardDistance() {

    }
}
