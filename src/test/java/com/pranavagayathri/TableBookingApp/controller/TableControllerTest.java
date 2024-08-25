package com.pranavagayathri.TableBookingApp.controller;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.service.RestaurantService;
import com.pranavagayathri.TableBookingApp.service.TableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TableControllerTest {

    @Mock
    private TableService tableService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private TableController tableController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for addTable()
    @Test
    public void testAddTable_Success() {
        Tables table = new Tables();
        table.setTableNumber(1L);
        table.setTotalSeats(4L);

        when(tableService.addTable(table)).thenReturn("Table added successfully");

        ResponseEntity<String> response = tableController.addTable(table);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Table added successfully", response.getBody());
    }

    @Test
    public void testAddTable_Failure() {
        Tables table = new Tables();
        table.setTableNumber(1L);
        table.setTotalSeats(4L);

        when(tableService.addTable(table)).thenReturn("Restaurant ID given is not available");

        ResponseEntity<String> response = tableController.addTable(table);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurant ID given is not available", response.getBody());
    }

    // Test for getAllTables()
    @Test
    public void testGetAllTables() {
        List<TableDTO> tableDTOList = new ArrayList<>();
        TableDTO tableDTO = new TableDTO();
        tableDTO.setTableId(1L);
        tableDTO.setTableNumber(1L);
        tableDTO.setTotalSeats(4L);
        tableDTOList.add(tableDTO);

        when(tableService.getAllTables()).thenReturn(tableDTOList);

        List<TableDTO> result = tableController.getAllTables();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTableId());
        assertEquals(1L, result.get(0).getTableNumber());
        assertEquals(4L, result.get(0).getTotalSeats());
    }
}
