package com.customerapp.CustomerAppApi.core.interfaces;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITableService {
    List<RestaurantTable> getAllRestaurantTables();
    Optional<RestaurantTable> getRestaurantTableById(UUID uuid);
}
