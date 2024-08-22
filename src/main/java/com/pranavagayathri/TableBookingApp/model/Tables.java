package com.pranavagayathri.TableBookingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "table_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tables {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tableId;


    @Column
    @NotNull
    private Long tableNumber;

    @Column
    @NotNull
    private Long totalSeats;

    @ManyToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "restaurantId",nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "tables",cascade =CascadeType.ALL )

    private List<Reservation> reservations;


}
