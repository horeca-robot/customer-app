package com.customerapp.CustomerAppApi.core.interfaces;

import edu.fontys.horecarobot.databaselibrary.models.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public interface IOrderService {
     ArrayList<Order> getAllOrders();
     void postOrder(Order order) throws Exception;
}
