package com.pranavagayathri.TableBookingApp.exceptions;

public class CannotAddRestaurantException extends Exception{
    public CannotAddRestaurantException() {
    }

    public CannotAddRestaurantException(String message) {
        super(message);
    }
}
