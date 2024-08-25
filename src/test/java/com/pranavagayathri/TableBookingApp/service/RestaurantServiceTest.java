package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.CannotAddRestaurantException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.helpermethods.EntityToDTO;
import com.pranavagayathri.TableBookingApp.helpermethods.TableToTableDTO;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepo restaurantRepo;

    @Mock
    private EntityToDTO entityToDTO;

    @Mock
    private TableToTableDTO tableToTableDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRestaurants() throws RestaurantNotFoundException {
        // Mocking data
        Restaurant restaurant1 = new Restaurant(1L, "Restaurant A", "Location A", 20L, "Description A", "City A", null, null, null);
        Restaurant restaurant2 = new Restaurant(2L, "Restaurant B", "Location B", 15L, "Description B", "City B", null, null, null);

        RestaurantDTO restaurantDTO1 = new RestaurantDTO(1L, "Restaurant A", "Location A", 20L, "Description A", null, null);
        RestaurantDTO restaurantDTO2 = new RestaurantDTO(2L, "Restaurant B", "Location B", 15L, "Description B", null, null);

        when(restaurantRepo.findAll()).thenReturn(Arrays.asList(restaurant1, restaurant2));
        when(entityToDTO.restaurantToRestaurantDTO(restaurant1)).thenReturn(restaurantDTO1);
        when(entityToDTO.restaurantToRestaurantDTO(restaurant2)).thenReturn(restaurantDTO2);

        // Test
        List<RestaurantDTO> result = restaurantService.getAllRestaurants();

        // Verifications
        assertEquals(2, result.size());
        assertEquals("Restaurant A", result.get(0).getRestaurantName());
        assertEquals("Restaurant B", result.get(1).getRestaurantName());
        verify(restaurantRepo, times(1)).findAll();
    }

    @Test
    void testGetRestaurantByIdSuccess() throws RestaurantNotFoundException {
        // Mocking data
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "Location A", 20L, "Description A", "City A", null, null, null);
        RestaurantDTO restaurantDTO = new RestaurantDTO(1L, "Restaurant A", "Location A", 20L, "Description A", null, null);

        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(restaurant));
        when(entityToDTO.restaurantToRestaurantDTO(restaurant)).thenReturn(restaurantDTO);

        // Test
        RestaurantDTO result = restaurantService.getRestaurantById(1L);

        // Verifications
        assertEquals(1L, result.getRestaurantId());
        assertEquals("Restaurant A", result.getRestaurantName());
        verify(restaurantRepo, times(1)).findById(1L);
        verify(entityToDTO, times(1)).restaurantToRestaurantDTO(restaurant);
    }

    @Test
    void testGetRestaurantByIdNotFound() {
        // Mocking the behavior when restaurant is not found
        when(restaurantRepo.findById(1L)).thenReturn(Optional.empty());

        // Test and verify exception
        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurantById(1L);
        });

        verify(restaurantRepo, times(1)).findById(1L);
    }

    @Test
    void testGetTablesByRestaurantId() {
        // Mocking data
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "Location A", 20L, "Description A", "City A", null, null, null);
        Tables table1 = new Tables(1, 10L, 4L, restaurant,null);
        Tables table2 = new Tables(2,11L,12L, restaurant,null);
        restaurant.setTables(Arrays.asList(table1, table2));

        TableDTO tableDTO1 = new TableDTO(1L, 10L, 4);
        TableDTO tableDTO2 = new TableDTO(2L, 11L, 6);

        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(restaurant));
        when(tableToTableDTO.tableDTOList(table1)).thenReturn(tableDTO1);
        when(tableToTableDTO.tableDTOList(table2)).thenReturn(tableDTO2);

        // Test
        List<TableDTO> result = restaurantService.getTablesByRestaurantId(1L);

        // Verifications
        assertEquals(2, result.size());
        assertEquals(10L, result.get(0).getTableNumber());
        assertEquals(11L, result.get(1).getTableNumber());
        verify(restaurantRepo, times(1)).findById(1L);
        verify(tableToTableDTO, times(1)).tableDTOList(table1);
        verify(tableToTableDTO, times(1)).tableDTOList(table2);
    }

    @Test
    void testAddRestaurantSuccess() throws CannotAddRestaurantException {
        // Mocking data
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "Location A", 20L, "Description A", "City A", null, null, null);

        // Test
        restaurantService.addRestaurant(restaurant);

        // Verifications
        verify(restaurantRepo, times(1)).save(restaurant);
    }

    @Test
    void testAddRestaurantFailure() {
        // Mocking exception
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "Location A", 20L, "Description A", "City A", null, null, null);
        doThrow(new RuntimeException("Database error")).when(restaurantRepo).save(restaurant);

        // Test and verify exception
        assertThrows(CannotAddRestaurantException.class, () -> {
            restaurantService.addRestaurant(restaurant);
        });

        verify(restaurantRepo, times(1)).save(restaurant);
    }
}
