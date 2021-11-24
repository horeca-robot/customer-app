package com.customerapp.CustomerAppApi.core.interfaces;

import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import com.customerapp.CustomerAppApi.models.OrderDto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
     List<RestaurantOrder> getAllOrders();
     Optional<RestaurantOrder> getOrderById(UUID id);
     List<RestaurantOrder> getAllOrdersFromTable(int restaurantTable_number);
     void postOrder(OrderDto order);
}
