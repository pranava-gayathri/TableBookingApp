package com.pranavagayathri.TableBookingApp.repository;

import com.pranavagayathri.TableBookingApp.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables,Integer> {

}
