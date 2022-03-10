package application.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reviewAuthor", cascade = CascadeType.ALL)
//    private List<Review> reviews;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User() {

    }
//    public List getReviews() {
//        return this.reviews;
//    }
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
