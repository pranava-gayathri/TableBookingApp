package com.pranavagayathri.TableBookingApp.helpermethods;

import com.pranavagayathri.TableBookingApp.dto.ReservationRequestDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.model.User;
import com.pranavagayathri.TableBookingApp.repository.ReservationRepository;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import com.pranavagayathri.TableBookingApp.repository.TableRepository;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
public class ReservationDTOtoReservation {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private TableRepository tableRepository;

    public Reservation reservationDTOtoReservation(ReservationRequestDTO reservationRequestDTO) throws UserNotFoundException, RestaurantNotFoundException,
            TableAlreadyReservedException, ReservationNotFoundException {
        Reservation reservation = new Reservation();
        if (reservationRequestDTO != null) {
            User user = userRepository.findById(reservationRequestDTO.getUserId()).orElse(null);
            if (user != null) {
                reservation.setUser(user);
            } else {
                throw new UserNotFoundException("user cannot be null");
            }

            Restaurant restaurant = restaurantRepo.findById(reservationRequestDTO.getRestaurantId()).orElse(null);
            if (restaurant != null) {
                reservation.setRestaurant(restaurant);
            } else {
                throw new RestaurantNotFoundException("restaurant cannot be null");
            }
            Tables tables = tableRepository.findById(reservationRequestDTO.getTableId()).orElse(null);
            if (tables != null) {
                reservation.setTables(tables);
            } else {
                throw new TableAlreadyReservedException("table can not be null");
            }

            reservation.setDate(reservationRequestDTO.getDate());
            reservation.setTime(reservationRequestDTO.getTime());
            reservation.setPartySize(reservationRequestDTO.getPartySize());

            if (!reservationRepository.isTableReserved(reservationRequestDTO.getRestaurantId()
                    , reservationRequestDTO.getTableId(), reservationRequestDTO.getDate(),
                    reservationRequestDTO.getTime())) {
                return reservation;
            } else {
                throw new TableAlreadyReservedException("table already reserved");
            }


        }
        else
        {

            throw new ReservationNotFoundException("reservation can not be null");

        }
    }




}
