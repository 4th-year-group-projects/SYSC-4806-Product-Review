package models;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User reviewAuthor;
    @ManyToOne
    private Product reviewedProduct;

    private int reviewRating;

    /**
     * Constructor to initialize a new review for a product
     * @param author
     * @param product
     * @param rating
     */
    public Review(User author, Product product, int rating) {
        this.reviewAuthor = author;
        this.reviewedProduct = product;
        this.reviewRating = rating;
    }

    /**
     * Default constructor
     */
    public Review() {
    }

    /**
     *
     * @return the id of the review
     */
    public Long getId() {
        return id;
    }

    /**
     * Setting the review id to a specified id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setting author to a specified user
     * @param user
     */
    public void setReviewAuthor(User user) {
        this.reviewAuthor = user;
    }

    /**
     *
     * @return the user who wrote the review
     */
    public User getReviewAuthor() {
        return this.reviewAuthor;
    }

    /**
     * Setting the product that was reviewed
     * @param product
     */
    public void setReviewedProduct(Product product) {
        this.reviewedProduct = product;
    }

    /**
     *
     * @return product that was reviewed
     */
    public Product getReviewedProduct() {
        return this.reviewedProduct;
    }

    /**
     * Setting the rating for the product review
     * @param rating
     */
    public void setReviewRating(int rating) {
        this.reviewRating = rating;
    }

    /**
     *
     * @return the rating for the product reviewed
     */
    public int getReviewRating() {
        return this.reviewRating;
    }
}
