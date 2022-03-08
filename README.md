# SYSC - 4806 : Trustworthy Product Reviews

# Project Setup
CircleCi build : [![CircleCI](https://circleci.com/gh/aubins11/SYSC-4806-Product-Review.svg?style=shield&circle-token=d7c5c4e00e2a5e6aa3ff02fe9a5260a84f0ef7ed)](<https://app.circleci.com/pipelines/github/aubins11/SYSC-4806-Product-Review>)
<br> Heroku link : 

# Project Description
1. Users review Products. 
2. Products could be identified by a link to the website where they are listed. 
3. A review consists of a star-rating and some text. 
4. Users can "follow" other users whose reviews they find valuable. 
   - The degree of separation (see Kevin Bacon) according to the "follow" link can also be displayed next to each reviewer (the assumption is that the "closer" a user is to you, the more trustworthy he/she should be to you).
5. A user can list
   - products for a given category by average rating
   - products for a given category by the average rating of only the users they follow. 
   - the users whose ratings are the most "similar" to their own using the Jaccard distance. 
   - the most followed users
6. Product reviews can  be ranked according to the  similarity score of the people who reviewed them. 

# Milestones

## Milestone 1 : Early Prototype

Application enables user registration and login. After login user can view their previous reviews, write a review for a new product and
look at reviews of a product written by other users. 

