package application.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long Id;

    @OneToMany(mappedBy ="product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Review> reviews;
    private String name, description, link;

    public Product(long Id, String name, String description, String link) {
        this.Id = Id;
        this.reviews = new ArrayList<Review>();
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public Product(String name, String description, String link) {
        this.reviews = new ArrayList<Review>();
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public Product() {}

    public long getId() {
        return this.Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    @Transient
    public void addReview(Review review) {
        this.reviews.add(review);
        review.setProduct(this);
    }
//
//    @Transient
//    public void removeReview()
    @Override
    @Transient
    public String toString() {
        return "{" +
                this.Id + ", " +
                this.name + ", " +
                this.description + ", " +
                this.link + ", " +
                this.reviews.toString() + "}";
    }
}
