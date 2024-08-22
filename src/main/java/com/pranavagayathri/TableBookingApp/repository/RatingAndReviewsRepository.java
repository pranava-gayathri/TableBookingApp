package com.pranavagayathri.TableBookingApp.repository;

import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingAndReviewsRepository extends JpaRepository<RatingsAndReviews ,Long> {
}
