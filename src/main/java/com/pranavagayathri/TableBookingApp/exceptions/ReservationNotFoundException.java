package com.pranavagayathri.TableBookingApp.exceptions;

public class ReservationNotFoundException extends Exception{

    public ReservationNotFoundException() {
        super();
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
