package com.pranavagayathri.TableBookingApp.repository;

import com.pranavagayathri.TableBookingApp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
//    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.tables=:tableId AND r.date=:date AND r.time=:time")
//    boolean isTableReserved(@Param("tableId") Integer tableId,
//                            @Param("date") LocalDate date,
//                            @Param("time") LocalTime time);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.restaurant.restaurantId=:restaurantId AND r.tables.tableId = :tableId AND r.date = :date AND r.time = :time")
    boolean isTableReserved(
            @Param("restaurantId") Long restaurantId,
            @Param("tableId") Integer tableId,
                            @Param("date") LocalDate date,
                            @Param("time") LocalTime time);
}
