package com.pranavagayathri.TableBookingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingsAndReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;

    private double rating;
    private String review;

    @ManyToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "restaurantId")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;


}
