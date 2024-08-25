package com.pranavagayathri.TableBookingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column

    private Long restaurantId;
    @Column

    private String restaurantName;
    @Column

    private String restaurantLocation;
    @Column

    private Long restaurantTotalTableCount;
    @Column

    private String restaurantDescription;
    @Column

    private String restaurantCity;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)

    private List<Tables> tables;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)

    private List<Reservation> reservations;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RatingsAndReviews> ratingsAndReviews;






}
