# SYSC - 4806 : Trustworthy Product Reviews

# Project Setup
CircleCi build : [![CircleCI](https://circleci.com/gh/aubins11/SYSC-4806-Product-Review.svg?style=shield&circle-token=d7c5c4e00e2a5e6aa3ff02fe9a5260a84f0ef7ed)](<https://app.circleci.com/pipelines/github/aubins11/SYSC-4806-Product-Review>)
<br> Heroku link : [Link to Heroku](https://product-review-application.herokuapp.com/)

# Project Description
Users review Products. These products could be identiﬁed by a link to the web site where they are listed. 
A review consists of a star-rating and some text. Users can also "follow" other users whose reviews they ﬁnd valuable. 

A user can then list products for a given category by average rating, or the average rating of only the users they follow. 
A user can also list the users whose ratings are the most "similar" to their own using the Jaccard distance (google it!). 

Product reviews can also be ranked according to the  similarity score of the people who reviewed them. 
Users can also list the most followed users. The degree of separation (see Kevin Bacon!) according to the "follow" link can also be displayed next to each reviewer 
(the assumption is that the "closer" a user is to you, the more trustworthy he/she should be to you).
# Milestones

## Milestone 1 : Early Prototype

Application enables user to:
* Register and login
* Create a product and write a review for a new product (the reviews are not associated with users yet) 
* View reviews of a product written

## Milestone 2 : Alpha Release

 Application now enables users to:
* View a list of all users with respective degree of separation and follow any user
* View a list of users they are following and users that are following them
* View the reviews they have written previously
* View a list of most followed users and their follower number
* Create a product and write a review for the products(reviews are now associated with users)
* Persisting data to database




