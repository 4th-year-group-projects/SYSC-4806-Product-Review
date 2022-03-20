package application.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String password;
    private String username;

    @OneToMany(mappedBy = "reviewAuthor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
