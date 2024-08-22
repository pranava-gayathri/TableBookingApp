package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService service;
    @GetMapping("/reservations")
    public List<ReservationDTO> getAllReservations(){
        return service.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public ReservationDTO getReservationById(@PathVariable long id) throws ReservationNotFoundException {
        return service.getReservationById(id);
    }
    @PostMapping("/addreservation")
    public void addReservation(@RequestBody Reservation reservation) throws TableAlreadyReservedException {
        service.addReservation(reservation);
    }
}
