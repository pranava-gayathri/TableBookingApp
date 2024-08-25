package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import com.pranavagayathri.TableBookingApp.repository.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @Mock
    private RestaurantRepo restaurantRepo;

    @InjectMocks
    private TableService tableService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for addTable() when restaurant exists
    @Test
    public void testAddTable_Success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1L);

        Tables table = new Tables();
        table.setTableNumber(1L);
        table.setTotalSeats(4L);
        table.setRestaurant(restaurant);

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);

        when(restaurantRepo.findAll()).thenReturn(restaurantList);
        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(restaurant));

        String result = tableService.addTable(table);

        assertEquals("Table added successfully", result);
        verify(tableRepository, times(1)).save(table);
    }

    // Test for addTable() when restaurant does not exist
    @Test
    public void testAddTable_Failure() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1L);

        Tables table = new Tables();
        table.setTableNumber(1L);
        table.setTotalSeats(4L);
        table.setRestaurant(restaurant);

        when(restaurantRepo.findById(1L)).thenReturn(Optional.empty());
        when(restaurantRepo.findAll()).thenReturn(new ArrayList<>());

        String result = tableService.addTable(table);

        assertEquals("Restaurant ID given is not available", result);
        verify(tableRepository, never()).save(table);
    }

    // Test for getAllTables()
    @Test
    public void testGetAllTables() {
        List<Tables> tablesList = new ArrayList<>();
        Tables table = new Tables();
        table.setTableId(1);
        table.setTableNumber(1L);
        table.setTotalSeats(4L);
        tablesList.add(table);

        when(tableRepository.findAll()).thenReturn(tablesList);

        List<TableDTO> result = tableService.getAllTables();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTableId());
        assertEquals(1L, result.get(0).getTableNumber());
        assertEquals(4L, result.get(0).getTotalSeats());
    }

}
