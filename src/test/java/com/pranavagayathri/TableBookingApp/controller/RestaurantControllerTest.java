package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.CannotAddRestaurantException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import com.pranavagayathri.TableBookingApp.service.RestaurantServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    @Mock
    private RestaurantServiceInterface restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for getAllRestaurants()
    @Test
    public void testGetAllRestaurants_Success() throws RestaurantNotFoundException {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(1L);
        restaurantDTO.setRestaurantName("Test Restaurant");
        restaurantDTO.setRestaurantLocation("Test Location");
        restaurantDTOList.add(restaurantDTO);

        when(restaurantService.getAllRestaurants()).thenReturn(restaurantDTOList);

        List<RestaurantDTO> result = restaurantController.getAllRestaurants();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getRestaurantId());
        assertEquals("Test Restaurant", result.get(0).getRestaurantName());
        assertEquals("Test Location", result.get(0).getRestaurantLocation());

        verify(restaurantService, times(1)).getAllRestaurants();
    }

    // Test for getRestaurantById() - Success Case
    @Test
    public void testGetRestaurantById_Success() throws RestaurantNotFoundException {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(1L);
        restaurantDTO.setRestaurantName("Test Restaurant");
        restaurantDTO.setRestaurantLocation("Test Location");

        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurantDTO);

        RestaurantDTO result = restaurantController.getRestaurantById(1L);

        assertEquals(1L, result.getRestaurantId());
        assertEquals("Test Restaurant", result.getRestaurantName());
        assertEquals("Test Location", result.getRestaurantLocation());

        verify(restaurantService, times(1)).getRestaurantById(1L);
    }

    // Test for getRestaurantById() - Failure Case (RestaurantNotFoundException)
    @Test
    public void testGetRestaurantById_Failure() throws RestaurantNotFoundException {
        when(restaurantService.getRestaurantById(1L)).thenThrow(new RestaurantNotFoundException("restaurant not found"));

        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantController.getRestaurantById(1L);
        });

        assertEquals("restaurant not found", exception.getMessage());

        verify(restaurantService, times(1)).getRestaurantById(1L);
    }

    // Test for getTablesByRestaurantId() - Success Case
    @Test
    public void testGetTablesByRestaurantId_Success() {
        List<TableDTO> tableDTOList = new ArrayList<>();
        TableDTO tableDTO = new TableDTO();
        tableDTO.setTableId(1L);
        tableDTO.setTableNumber(101L);
        tableDTO.setTotalSeats(4L);
        tableDTOList.add(tableDTO);

        when(restaurantService.getTablesByRestaurantId(1L)).thenReturn(tableDTOList);

        List<TableDTO> result = restaurantController.getTablesByRestaurantId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTableId());
        assertEquals(101L, result.get(0).getTableNumber());
        assertEquals(4L, result.get(0).getTotalSeats());

        verify(restaurantService, times(1)).getTablesByRestaurantId(1L);
    }

    // Test for addRestaurant()
    @Test
    public void testAddRestaurant() throws CannotAddRestaurantException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1L);
        restaurant.setRestaurantName("New Restaurant");

        doNothing().when(restaurantService).addRestaurant(restaurant);

        restaurantController.addRestaurant(restaurant);

        verify(restaurantService, times(1)).addRestaurant(restaurant);
    }

}
