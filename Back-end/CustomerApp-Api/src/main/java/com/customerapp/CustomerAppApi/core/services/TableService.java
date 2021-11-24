package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ITableService;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantTable;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService implements ITableService {

    private final RestaurantTableRepository restaurantTableRepository;

    public TableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<RestaurantTable> getAllRestaurantTables() {
        List<RestaurantTable> tables =  restaurantTableRepository.findAll();
        Collections.sort(tables, Comparator.comparing(RestaurantTable::getTableNumber));
        return tables;
    }


    public Optional<RestaurantTable> getRestaurantTableById(UUID uuid) {
        return restaurantTableRepository.findById(uuid);
    }
}
