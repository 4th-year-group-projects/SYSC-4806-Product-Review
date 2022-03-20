package application.models;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User reviewAuthor;
    @ManyToOne(fetch = FetchType.LAZY)//(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private int reviewRating;
    private String reviewComment;


    /**
     * Default constructor
     */
    public Review() {
    }

    /**
     * Constructor to initialize a new review for a product
     * @param productName to be initialized to
     * @param rating to be initialized to
     * @param comment to be initialized to
     */
    public Review(String productName, Product product, int rating, String comment) {
       // this.reviewAuthor = author;
        this.product = product;
        this.reviewRating = rating;
        this.reviewComment = comment;
    }

    /**
     * Constructor to initialize a new review for a product
     * @param rating to be initialized to
     * @param comment to be initialized to
     */
    public Review(long id, Product product, int rating, String comment) {
        this.id = id;
      //  this.reviewAuthor = author;
        this.product = product;
        this.reviewRating = rating;
        this.reviewComment = comment;
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
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     *
     * @return product that was reviewed
     */
    public Product getReviewedProduct() {
        return this.product;
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

    @Override
    @Transient
    public String toString() {
        return "{" +
                this.id + ", " +
                this.reviewRating + ", " +
                this.reviewComment + "}";
    }

}
