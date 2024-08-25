package com.pranavagayathri.TableBookingApp.helpermethods;

import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.model.Tables;
import org.springframework.stereotype.Component;

@Component
public class TableToTableDTO {

    public TableDTO tableDTOList(Tables table) {

        TableDTO tdto = new TableDTO();
        tdto.setTotalSeats(table.getTotalSeats());
        tdto.setTableNumber(table.getTableNumber());
        tdto.setTableId(table.getTableId());
        return tdto;


    }
}
