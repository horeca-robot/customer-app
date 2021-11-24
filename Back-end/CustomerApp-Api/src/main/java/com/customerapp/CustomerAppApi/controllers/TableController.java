package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.ITableService;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/table/")
public class TableController {

    private final ITableService tableService;

    @Autowired
    public TableController(ITableService tableService) {
        this.tableService = tableService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<RestaurantTable> getAllTables()
    {
        return tableService.getAllRestaurantTables();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Integer getTableNumberById(@RequestParam String id)
    {
        var table = tableService.getRestaurantTableById(UUID.fromString(id));
        if(table.isPresent())
        {
            return table.get().getTableNumber();
        }
        else {
            return null;
        }
    }
}
