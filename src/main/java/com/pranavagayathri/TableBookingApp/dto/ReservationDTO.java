package com.pranavagayathri.TableBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private long reservationId;
    private LocalTime time;
    private LocalDate date;
    private UserDTO userDTO;
    private long restaurantId;
    private String restaurantName;
    private long tableId;
    private long tableNumber;
}
