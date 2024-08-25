package com.pranavagayathri.TableBookingApp.helpermethods;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import org.springframework.stereotype.Component;

@Component
public class RatingReviewsToRatingReviewsDTO {

    public RatingAndReviewsDTO ratingAndReviewsDTO(RatingsAndReviews ratingsAndReviews){
        RatingAndReviewsDTO ratingAndReviewsDTO=new RatingAndReviewsDTO();
        ratingAndReviewsDTO.setRating(ratingsAndReviews.getRating());
        ratingAndReviewsDTO.setReview(ratingsAndReviews.getReview());
        return ratingAndReviewsDTO;
    }
}
