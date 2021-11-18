package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.ITableService;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<RestaurantTable> getAllTableNumbers()
    {
        return tableService.getAllRestaurantTables();
    }
}
