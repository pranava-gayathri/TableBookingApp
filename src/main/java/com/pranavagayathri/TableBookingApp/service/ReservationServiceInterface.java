package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationRequestDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ReservationServiceInterface {
    List<ReservationDTO> getAllReservations() throws ReservationNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException, UserNotFoundException;

    ReservationDTO getReservationById(long id) throws ReservationNotFoundException, UserNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException;

    String addReservation(ReservationRequestDTO reservationRequestDTO) throws TableAlreadyReservedException, ReservationNotFoundException, UserNotFoundException, RestaurantNotFoundException;
}
