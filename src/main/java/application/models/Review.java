package application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    private int rating;
    private String name;

    public Review(Product product, int rating, String text) {
    }

    public Review() {

    }

    public String getUrl() {
        return url;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
