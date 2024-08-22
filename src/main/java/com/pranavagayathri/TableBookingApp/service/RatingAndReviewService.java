package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import com.pranavagayathri.TableBookingApp.repository.RatingAndReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingAndReviewService {
    @Autowired
    private RatingAndReviewsRepository repo;
    public List<RatingAndReviewsDTO> getAllRatingAndReviews(){
        List<RatingsAndReviews> ratingsAndReviews=repo.findAll();
        List<RatingAndReviewsDTO> ratingAndReviewsDTOS=new ArrayList<>();
        for(RatingsAndReviews rr: ratingsAndReviews){
            RatingAndReviewsDTO rrdto =new RatingAndReviewsDTO();
            rrdto.setReview(rr.getReview());
            rrdto.setRating(rr.getRating());
            ratingAndReviewsDTOS.add(rrdto);

        }
        return ratingAndReviewsDTOS;
    }

    public void addRatingAndReview(RatingsAndReviews ratingsAndReviews) {
        repo.save(ratingsAndReviews);
    }
}
