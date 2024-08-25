package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;

import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import com.pranavagayathri.TableBookingApp.service.TableService;
import com.pranavagayathri.TableBookingApp.service.TableServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController {
    @Autowired
    private TableServiceInterface service;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addtable")
    public ResponseEntity<String> addTable(@RequestBody Tables table){
        String str=service.addTable(table);
        return ResponseEntity.ok(str);
    }
    @RequestMapping("/getalltables")
    public List<TableDTO> getAllTables(){
        return service.getAllTables();
    }


}
