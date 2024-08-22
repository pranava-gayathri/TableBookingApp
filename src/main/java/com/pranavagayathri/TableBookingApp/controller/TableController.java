package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Restaurant;

import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import com.pranavagayathri.TableBookingApp.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {
    @Autowired
    private TableService service;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addtable")
    public ResponseEntity<String> addTable(@RequestBody Tables table){
        String str=service.addTable(table);
        return ResponseEntity.ok(str);
    }
    @RequestMapping("/getalltables")
    public List<TableDTO> getAllTables(){
        //List<Tables> allTables=service.getAllTables();
//        List<TableDTO> dtoTables=new ArrayList<>();
//        for(Tables table:tables){
//            TableDTO tableDTO=new TableDTO();
//            tableDTO.setTableNumber(table.getTableNumber());
//            tableDTO.setTableId(table.getTableId());
//            tableDTO.setTotalSeats(table.getTotalSeats());
//            RestaurantDTO rdto=new RestaurantDTO();
//            rdto.setRestaurantId(table.getRestaurant().getRestaurantId());
//            rdto.setRestaurantLocation(table.getRestaurant().getRestaurantLocation());
//            rdto.setRestaurantName(table.getRestaurant().getRestaurantName());
//            rdto.setRestaurantDescription(table.getRestaurant().getRestaurantDescription());
//            rdto.setRestaurantTotalTableCount(table.getRestaurant().getRestaurantTotalTableCount());
//            tableDTO.setRestaurantDTO(rdto);
//            dtoTables.add(tableDTO);
//        }

        return service.getAllTables();


    }


}
