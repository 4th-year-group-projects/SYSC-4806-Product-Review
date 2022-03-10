package application.models;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToOne//(fetch = FetchType.EAGER)
//   // @JoinColumn(name = "reviewAuthor_ID")
//    private User reviewAuthor;
//    @ManyToOne//(cascade = CascadeType.ALL)
//    private Product reviewedProduct;

    private String reviewedProduct;
    private String reviewRating;
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
    public Review(String productName, String rating, String comment) {
       // this.reviewAuthor = author;
        this.reviewedProduct = productName;
        this.reviewRating = rating;
        this.reviewComment = comment;
    }

    /**
     * Constructor to initialize a new review for a product
     * @param productName to be initialized to
     * @param rating to be initialized to
     * @param comment to be initialized to
     */
    public Review(long id, String productName, String rating, String comment) {
        this.id = id;
      //  this.reviewAuthor = author;
        this.reviewedProduct = productName;
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

//    /**
//     * Setting author to a specified user
//     * @param user user to be set to
//     */
//    public void setReviewAuthor(User user) {
//        this.reviewAuthor = user;
//    }
//
//    /**
//     *
//     * @return the user who wrote the review
//     */
//    public User getReviewAuthor() {
//        return this.reviewAuthor;
//    }

    /**
     * Setting the product that was reviewed
     * @param product product to be set to
     */
    public void setReviewedProduct(String product) {
        this.reviewedProduct = product;
    }

    /**
     *
     * @return product that was reviewed
     */
    public String getReviewedProduct() {
        return this.reviewedProduct;
    }

    /**
     * Setting the rating for the product review
     * @param rating rating to be set to
     */
    public void setReviewRating(String rating) {
        this.reviewRating = rating;
    }

    /**
     *
     * @return the rating for the product reviewed
     */
    public String getReviewRating() {
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
                this.reviewedProduct + ", " +
                this.reviewRating + ", " +
                this.reviewComment + "}";
    }

}
