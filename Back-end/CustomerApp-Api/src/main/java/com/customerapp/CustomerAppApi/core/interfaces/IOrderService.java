package com.customerapp.CustomerAppApi.core.interfaces;

import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public interface IOrderService {
     ArrayList<RestaurantOrder> getAllOrders();
     void postOrder(RestaurantOrder order) throws Exception;
}
