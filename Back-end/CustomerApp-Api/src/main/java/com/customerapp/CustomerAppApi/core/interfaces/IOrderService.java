package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.RestaurantOrder;


import java.util.ArrayList;

public interface IOrderService {
     ArrayList<RestaurantOrder> getAllOrders();
     void postOrder(RestaurantOrder order);
}
