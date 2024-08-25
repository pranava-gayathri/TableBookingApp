package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationRequestDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.service.ReservationService;
import com.pranavagayathri.TableBookingApp.service.ReservationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationServiceInterface service;
    @GetMapping("/reservations")
    public List<ReservationDTO> getAllReservations() throws UserNotFoundException, ReservationNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        return service.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public ReservationDTO getReservationById(@PathVariable long id) throws ReservationNotFoundException, UserNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        return service.getReservationById(id);
    }
    @PostMapping("/addreservation")
    public ResponseEntity<String> addReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) throws TableAlreadyReservedException, ReservationNotFoundException, UserNotFoundException, RestaurantNotFoundException {
        String str=service.addReservation(reservationRequestDTO);
        return ResponseEntity.ok(str);
    }
}
