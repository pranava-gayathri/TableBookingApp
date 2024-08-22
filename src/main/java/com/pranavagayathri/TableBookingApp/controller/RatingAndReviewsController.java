package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import com.pranavagayathri.TableBookingApp.service.RatingAndReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingAndReviewsController {
    @Autowired
    private RatingAndReviewService service;

    @GetMapping("/ratingandreviews")
    public List<RatingAndReviewsDTO> getAllRatingAndReviews(){
        return service.getAllRatingAndReviews();
    }
    @PostMapping("/addratingandreview")
    public void addRatingAndReview(@RequestBody RatingsAndReviews ratingsAndReviews){
        service.addRatingAndReview(ratingsAndReviews);
    }
}
