package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.RestaurantOrder;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
     List<RestaurantOrder> getAllOrders();
     Optional<RestaurantOrder> getOrderById(UUID id);
     List<RestaurantOrder> getAllOrdersFromTable(int restaurantTable_number);
     void postOrder(RestaurantOrder order);
}
