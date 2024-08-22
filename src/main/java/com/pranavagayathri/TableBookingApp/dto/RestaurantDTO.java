package com.pranavagayathri.TableBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RestaurantDTO {
    private long restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private long restaurantTotalTableCount;
    private String restaurantDescription;
    private List<TableDTO> tableDTOList;
    private List<RatingAndReviewsDTO> ratingAndReviewsDTOS;

}
