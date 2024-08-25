package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationRequestDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.helpermethods.ReservationDTOtoReservation;
import com.pranavagayathri.TableBookingApp.helpermethods.ReservationToReservationDTO;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.repository.ReservationRepository;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import com.pranavagayathri.TableBookingApp.repository.TableRepository;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements ReservationServiceInterface {
    @Autowired
    private ReservationRepository repo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private ReservationToReservationDTO toReservationDTO;
    @Autowired
    private ReservationDTOtoReservation reservationDTOtoReservation;



    @Override
    public List<ReservationDTO> getAllReservations() throws ReservationNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException, UserNotFoundException {
        List<ReservationDTO> reservationDTOS=new ArrayList<>();
        List<Reservation> reservations=repo.findAll();
        for(Reservation r:reservations){
            ReservationDTO reservationDTO=toReservationDTO.reservationToReservationDTO(r);
            reservationDTOS.add(reservationDTO);
        }
        return reservationDTOS;

    }

    @Override
    public ReservationDTO getReservationById(long id) throws ReservationNotFoundException, UserNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        Reservation reservation=repo.findById(id).orElse(null);
        if(reservation!=null){
            return toReservationDTO.reservationToReservationDTO(reservation);
        }
        else{
            throw new ReservationNotFoundException("reservation not found");
        }

    }






    @Override
    public String addReservation(ReservationRequestDTO reservationRequestDTO) throws TableAlreadyReservedException, ReservationNotFoundException, UserNotFoundException, RestaurantNotFoundException {

        Reservation reservation=reservationDTOtoReservation.reservationDTOtoReservation(reservationRequestDTO);
        if(reservation!=null){
            repo.save(reservation);
            return "Reservation created successfully.";
        }
        else{
            throw new ReservationNotFoundException("reservation can not be null");
        }



    }
}





