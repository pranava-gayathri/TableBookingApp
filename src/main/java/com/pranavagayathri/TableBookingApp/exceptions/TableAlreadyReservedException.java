package com.pranavagayathri.TableBookingApp.exceptions;

public class TableAlreadyReservedException extends Exception{
    public TableAlreadyReservedException() {
        super();
    }

    public TableAlreadyReservedException(String message) {
        super(message);
    }
}
