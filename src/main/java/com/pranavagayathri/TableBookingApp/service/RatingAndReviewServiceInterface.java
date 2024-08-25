package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RatingAndReviewServiceInterface {

    // Fetch all ratings and reviews and return a list of RatingAndReviewsDTO
    List<RatingAndReviewsDTO> getAllRatingAndReviews();

    // Add a new rating and review
    void addRatingAndReview(RatingsAndReviews ratingsAndReviews);
}
