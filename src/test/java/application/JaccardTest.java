package application;

import application.models.Product;
import application.models.Review;
import application.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JaccardTest {
    private static final int NUM_USERS = 7;
    private static final int NUM_PRODUCTS = 5;
    private ArrayList<Product> products;
    private ArrayList<User> users;
    private ArrayList<Review> reviews;

    @BeforeEach
    public void setup() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        reviews = new ArrayList<>();
        // Instantiate Products
        for(int i = 0; i < NUM_PRODUCTS; i++) {
            products.add(new Product(i,"p"+i, "desc"+i, "url"+i, Category.BOOKS));
        }
        // Instantiate Users
        for(int i = 0; i < NUM_USERS; i++) {
            users.add(new User("user"+i, "pass"+i));
        }
        for(int i = 0; i < NUM_PRODUCTS; i++) {
            Product p = products.get(i);
            reviews.add(users.get(0).writeReview(p, 5, "5 Stars"));
            reviews.add(users.get(1).writeReview(p, 4, "4 Stars"));
            reviews.add(users.get(2).writeReview(p, 3, "3 Stars"));
            reviews.add(users.get(3).writeReview(p, 2, "2 Stars"));
            reviews.add(users.get(4).writeReview(p, 4, "4 Stars"));
            reviews.add(users.get(5).writeReview(p, i+1, "increasing stars"));
            reviews.add(users.get(6).writeReview(p, 5-i, "decreasing stars"));
        }

    }

    @Test
    public void testCalculateSingleJaccardDistance() {
        // Should be Jaccard Distance = 1 for users[1] with users[0] and users[2] and users[4]
        assertEquals(1.0, users.get(1).calculateJaccardDistance(users.get(0)), 0.01);
        assertEquals(1.0, users.get(1).calculateJaccardDistance(users.get(2)), 0.01);
        assertEquals(1.0, users.get(1).calculateJaccardDistance(users.get(4)), 0.01);

        // Should be Jaccard Distance = 0 for users[0] with users[3] and users[2]
        assertEquals(0.0, users.get(0).calculateJaccardDistance(users.get(3)), 0.01);
        assertEquals(0.0, users.get(0).calculateJaccardDistance(users.get(2)), 0.01);

        // Should be Jaccard Distance = 0.2 for users[5] and users[6]
        assertEquals(0.2, users.get(5).calculateJaccardDistance(users.get(6)), 0.01);

        // Should be Jaccard Distance = 0.4 for users[0] and users[6]
        assertEquals(0.4, users.get(0).calculateJaccardDistance(users.get(6)), 0.01);
    }

    @Test
    public void testCalculateJaccardDistanceSymmetry() {
        // Jaccard distance between any two users should be symmetrical
        double jaccard1 = users.get(5).calculateJaccardDistance(users.get(6));
        double jaccard2 = users.get(6).calculateJaccardDistance(users.get(5));
        assertEquals(jaccard1, jaccard2, 0.01);
    }

    @Test
    public void testSortByJaccardDistance() {
        // Calculate all distances between user 0 and all other users
        User user0 = users.get(0);
        HashSet<User> comparisons = new HashSet<>();
        comparisons.add(users.get(1));
        comparisons.add(users.get(3));
        comparisons.add(users.get(6));
        ArrayList<JaccardUserHelper> distances = user0.sortByJaccardDistance(comparisons);
        assertEquals(users.get(1), distances.get(0).getUser());
        assertEquals(users.get(6), distances.get(1).getUser());
        assertEquals(users.get(3), distances.get(2).getUser());
    }
}
