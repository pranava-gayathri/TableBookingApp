package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.UserDTO;
import com.pranavagayathri.TableBookingApp.exceptions.ReservationNotFoundException;
import com.pranavagayathri.TableBookingApp.exceptions.TableAlreadyReservedException;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.repository.ReservationRepository;
import com.pranavagayathri.TableBookingApp.repository.TableRepository;
import com.pranavagayathri.TableBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TableRepository tableRepository;



    public List<ReservationDTO> getAllReservations() {
        return  getReservationDTOList();
    }

    public ReservationDTO getReservationById(long id) throws ReservationNotFoundException {
        Optional<Reservation> r1=repo.findById(id);
        if(!r1.isPresent()){
            throw new ReservationNotFoundException("reservation not found");
        }
        else{
        ReservationDTO rdto=new ReservationDTO();
        Reservation r=repo.findById(id).get();
        //Reservation r=repo.findById(id).orElseThrow();
        rdto.setReservationId(r.getReservationId());
        rdto.setDate(r.getDate());
        rdto.setTime(r.getTime());
        rdto.setRestaurantId(r.getRestaurant().getRestaurantId());
        rdto.setRestaurantName(r.getRestaurant().getRestaurantName());
        UserDTO udto = getUserDTO(r);
        rdto.setUserDTO(udto);
        rdto.setTableId(r.getTables().getTableId());
        rdto.setTableNumber(r.getTables().getTableNumber());

        return rdto;}

    }

    private static UserDTO getUserDTO(Reservation r) {
        UserDTO udto= new UserDTO();
        udto.setUserId(r.getUser().getUserId());
        udto.setPhone(r.getUser().getPhone());
        udto.setUserName(r.getUser().getUserName());
        return udto;
    }

    private List<ReservationDTO> getReservationDTOList() {
        List<Reservation> reservations=repo.findAll();
        List<ReservationDTO> reservationDTOList=new ArrayList<>();

        for(Reservation r:reservations){
            ReservationDTO rdto=new ReservationDTO();
            rdto.setReservationId(r.getReservationId());
            rdto.setDate(r.getDate());
            rdto.setTime(r.getTime());
            rdto.setRestaurantId(r.getRestaurant().getRestaurantId());
            rdto.setRestaurantName(r.getRestaurant().getRestaurantName());
            UserDTO udto= getUserDTO(r);
            rdto.setUserDTO(udto);
            rdto.setTableId(r.getTables().getTableId());
            rdto.setTableNumber(r.getTables().getTableId());


            reservationDTOList.add(rdto);


        }
        return reservationDTOList;
    }



    public void addReservation(Reservation reservation) throws TableAlreadyReservedException {

        boolean isReserved= repo.isTableReserved(reservation.getTables().getTableId(),
                reservation.getDate(),reservation.getTime());
        if(isReserved){
            throw new TableAlreadyReservedException("table is reserved already...");
        }
        repo.save(reservation);

    }


}
