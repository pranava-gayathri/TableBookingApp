package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.CannotAddRestaurantException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Restaurant;

import java.util.List;

public interface RestaurantServiceInterface {
    //@Override
    List<RestaurantDTO> getAllRestaurants() throws RestaurantNotFoundException;

    //@Override
    RestaurantDTO getRestaurantById(long id) throws RestaurantNotFoundException;

    //@Override
    List<TableDTO> getTablesByRestaurantId(long id);

    void addRestaurant(Restaurant restaurant) throws CannotAddRestaurantException;
}
