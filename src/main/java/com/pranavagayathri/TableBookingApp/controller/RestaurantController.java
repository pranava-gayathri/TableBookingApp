package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.CannotAddRestaurantException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.service.RestaurantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantServiceInterface service;

    @RequestMapping("/restaurants")
    public List<RestaurantDTO> getAllRestaurants() throws RestaurantNotFoundException {

        return service.getAllRestaurants();

    }

    @RequestMapping("/getrestaurantbyid/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable long id) throws RestaurantNotFoundException {
        return service.getRestaurantById(id);
    }

    @RequestMapping("/tables/{restaurantId}")
    public List<TableDTO> getTablesByRestaurantId(@PathVariable long restaurantId){
        return service.getTablesByRestaurantId(restaurantId);
    }

    @PostMapping("/addrestaurant")
    public void addRestaurant(@RequestBody Restaurant restaurant) throws CannotAddRestaurantException {
        service.addRestaurant(restaurant);
    }
}
