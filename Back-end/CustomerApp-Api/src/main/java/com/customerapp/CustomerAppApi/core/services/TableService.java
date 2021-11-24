package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ITableService;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantTable;
import com.customerapp.CustomerAppApi.databaselibrary.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableService implements ITableService {

    private final RestaurantTableRepository restaurantTableRepository;

    public TableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findAll();
    }

    public Optional<RestaurantTable> getRestaurantTableById(UUID uuid) {
        return restaurantTableRepository.findById(uuid);
    }
}
