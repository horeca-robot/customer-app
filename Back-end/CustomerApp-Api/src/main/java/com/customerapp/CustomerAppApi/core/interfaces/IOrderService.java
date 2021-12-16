package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.models.PostOrderDto;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
     List<RestaurantOrder> getAllOrders();
     Optional<RestaurantOrder> getOrderById(UUID id);
     List<RestaurantOrder> getAllOrdersFromTable(UUID restaurantTable_id);
     void postOrder(PostOrderDto order);
}
