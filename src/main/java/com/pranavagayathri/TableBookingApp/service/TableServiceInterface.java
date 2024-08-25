package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Tables;

import java.util.List;

public interface TableServiceInterface {
    String addTable(Tables table);

    List<TableDTO> getAllTables();
}
