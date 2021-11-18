package com.customerapp.CustomerAppApi.core.interfaces;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantTable;

import java.util.List;

public interface ITableService {
    List<RestaurantTable> getAllRestaurantTables();
}
