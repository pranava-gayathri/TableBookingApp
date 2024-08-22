package com.pranavagayathri.TableBookingApp.dto;

import com.pranavagayathri.TableBookingApp.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private long tableId;
    private long tableNumber;
    private long totalSeats;
    //private RestaurantDTO restaurantDTO;


}
