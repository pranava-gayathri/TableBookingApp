package com.pranavagayathri.TableBookingApp.helpermethods;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationToReservationDTO {
    @Autowired
    private EntityToDTO entityToDTO;

    public ReservationDTO reservationToReservationDTO(Reservation reservation) throws ReservationNotFoundException, UserNotFoundException, RestaurantNotFoundException, TableAlreadyReservedException {
        if(reservation!=null) {
            ReservationDTO reservationDTO=new ReservationDTO();
            reservationDTO.setReservationId(reservation.getReservationId());
            reservationDTO.setTime(reservation.getTime());
            reservationDTO.setDate(reservation.getDate());
            if(reservation.getUser()!=null){
                UserDTO udto=entityToDTO.userToUserDTO(reservation.getUser());
                reservationDTO.setUserDTO(udto);
            }
            else{
                throw new UserNotFoundException("user cannot be null");
            }

            if(reservation.getRestaurant()!=null) {
                reservationDTO.setRestaurantId(reservation.getRestaurant().getRestaurantId());
                reservationDTO.setRestaurantName(reservation.getRestaurant().getRestaurantName());
            }
            else{
                throw new RestaurantNotFoundException("restaurant can not be empty");
            }
            if(reservation.getTables()!=null){
                reservationDTO.setTableId(reservation.getTables().getTableId());
                reservationDTO.setTableNumber(reservation.getTables().getTableNumber());
            }
            else{
                throw new TableAlreadyReservedException("table object cannot be empty");
            }
            return reservationDTO;

        }
        else{
            throw new ReservationNotFoundException("reservation not found");
        }

    }
}
