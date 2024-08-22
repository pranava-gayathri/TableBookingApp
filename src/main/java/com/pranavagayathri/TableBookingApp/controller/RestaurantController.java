package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @RequestMapping("/restaurants")
    public List<RestaurantDTO> getAllRestaurants(){

        return service.getAllRestaurants();

    }

    @RequestMapping("/getrestaurantbyid/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable long id) throws RestaurantNotFoundException {
        return service.getRestaurantById(id);
    }

    @PostMapping("/addrestaurant")
    public void addRestaurant(@RequestBody Restaurant restaurant){
        service.addRestaurant(restaurant);
    }
}
