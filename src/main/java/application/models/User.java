package application.models;

import application.JaccardUserHelper;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String password;
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviewList;

    @OneToMany
    private Set<User> followersList;

    @OneToMany
    private Set<User> followingList;


    public User() {}

    public User(String name, String password) {
        this.username = name;
        this.password = password;
        this.reviewList = new HashSet<>();
        this.followersList = new HashSet<>();
        this.followingList = new HashSet<>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public Set<Review> getReviewList() {
        return reviewList;
    }

    public Set<User> getFollowersList() {
        return followersList;
    }

    public Set<User> getFollowingList() {
        return followingList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Review writeReview(Product product, int rating, String text){
//        Review review = new Review(product, rating, text);
//        this.reviewList.add(review);
//
//        return review;
//    }

    /**
     * Method used for jaccard distance that will return a mapping between the products reviewed and the ratings.
     * @return HashMap with Product keys and Integer rating values
     */
    @Transient
    public HashMap<Product, Integer> getProductRatingMap() {
        // OPTIONAL CHANGE: WE STORE THIS HASHMAP AND UPDATE IT WHEN NEW REVIEW IS MADE, TIME EFFICIENT BUT NOT
        // SPACE EFFICIENT
        HashMap<Product, Integer> productRatingMap = new HashMap<>();
        for (Review r : reviewList) {
            productRatingMap.put(r.getReviewedProduct(), r.getReviewRating());
        }
        return productRatingMap;
    }

    @Transient
    /**
     * Jaccard distance is calculated as the intersection of two sets divided by the union of the same two sets.
     * While this will show the people who reviewed the same set of products as you, it will not tell you how they
     * reviewed them. Since we want this metric to show users whose ratings are "similar" to theirs, we will add an
     * additional metric where reviews are only considered to be part of the intersecting set if they are within +/-
     * 1 star (can be redefined).
     */
    public double calculateJaccardDistance(User user) {
        // Get all products in common
        HashMap<Product, Integer> thisUserRatings = this.getProductRatingMap();
        HashMap<Product, Integer> otherUserRatings = user.getProductRatingMap();
        Set<Product> commonProducts = new HashSet<>(thisUserRatings.keySet());
        commonProducts.retainAll(otherUserRatings.keySet());
        // Union = size of both sets - size of intersection (commonProducts)
        int num_union = thisUserRatings.size() + otherUserRatings.size() - commonProducts.size();
        // Actual intersection = all reviews with +/- 1 star
        int num_intersection = 0;
        for (Product p : commonProducts) {
            if(abs(thisUserRatings.get(p) - otherUserRatings.get(p)) <= 1) {
                num_intersection++;
            }
        }
        return num_intersection/num_union;
    }

    @Transient
    ArrayList<JaccardUserHelper> calculateJaccardDistances(Set<User> users) {
        ArrayList<JaccardUserHelper> jaccardDistances = new ArrayList<>();
        for(User u : users) {
            jaccardDistances.add(new JaccardUserHelper(u, this.calculateJaccardDistance(u)));
        }
        return jaccardDistances;
    }

    @Transient
    public ArrayList<JaccardUserHelper> sortByJaccardDistance(Set<User> users) {
        ArrayList<JaccardUserHelper> distances = calculateJaccardDistances(users);
        Collections.sort(distances);
        return distances;
    }

    /*
    @Transient
    public double calculateJaccardDistanceForCategory(User user, Category category) {
        return -1.0;
    }

    @Transient
    public Set<User> sortByJaccardDistance(Set<User> users, Category category) {
        return null;
    }
    */



    public void followUser(User user){
        this.followingList.add(user);
        user.addFollower(this);
    }

    public void unFollowUser(User user){
        this.followingList.remove(user);
        user.removeFollower(this);
    }

    public void addFollower(User follower){
        this.followersList.add(follower);
    }

    public void removeFollower(User follower){
        this.followersList.remove(follower);
    }
}
