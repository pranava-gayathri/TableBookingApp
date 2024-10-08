package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import com.pranavagayathri.TableBookingApp.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TableService implements TableServiceInterface {

    @Autowired
    private TableRepository repo;
    @Autowired
    private RestaurantRepo restaurantRepo;


    @Override
    public String addTable(Tables table) {
        List<Restaurant> restaurants=restaurantRepo.findAll();
        long id=table.getRestaurant().getRestaurantId();
        Restaurant res=restaurantRepo.findById(id).orElse(null);
        if(restaurants.contains(res)){
            repo.save(table);
            return "Table added successfully";
        }
        else{
            return "Restaurant ID given is not available";
        }


    }


    @Override
    public List<TableDTO> getAllTables() {
        List<Tables> tables=repo.findAll();
        List<TableDTO> tableDTOList=new ArrayList<>();
        for(Tables t:tables){
            TableDTO tableDTO=new TableDTO();
            tableDTO.setTableNumber(t.getTableNumber());
            tableDTO.setTableId(t.getTableId());
            tableDTO.setTotalSeats(t.getTotalSeats());
            tableDTOList.add(tableDTO);
        }
        return tableDTOList;
    }


}
