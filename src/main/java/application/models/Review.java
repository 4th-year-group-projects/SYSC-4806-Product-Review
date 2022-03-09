package application.models;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User reviewAuthor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product reviewedProduct;

    private int reviewRating;
    private String reviewComment;
    /**
     * Constructor to initialize a new review for a product
     * @param author to be initialized to
     * @param product to be initialized to
     * @param rating to be initialized to
     * @param comment to be initialized to
     */
    public Review(User author, Product product, int rating, String comment) {
        this.reviewAuthor = author;
        this.reviewedProduct = product;
        this.reviewRating = rating;
        this.reviewComment = comment;
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
     * @param id id to be set to
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setting author to a specified user
     * @param user user to be set to
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
     * @param product product to be set to
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
     * @param rating rating to be set to
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

    /**
     * Setting review comments
     * @param comment comments for product review
     */
    public void setReviewComment(String comment) {
        this.reviewComment = comment;
    }

    /**
     * Getting comments for review
     * @return comment for the specified review
     */
    public String getReviewComment() {
        return this.reviewComment;
    }
}
