package com.pranavagayathri.TableBookingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    @Column
    private LocalTime time;

    @Column
    private  LocalDate date;

    @Column
    private int partySize;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "tableId",referencedColumnName = "tableId")
    private Tables tables;

}
