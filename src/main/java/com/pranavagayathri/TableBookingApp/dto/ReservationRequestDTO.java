package com.pranavagayathri.TableBookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {

    private long reservationId;
    private long userId;
    private LocalDate date;
    private LocalTime time;
    private long restaurantId;
    private int tableId;
    private int partySize;


}
