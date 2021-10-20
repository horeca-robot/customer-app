package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import databaselibrary.models.RestaurantOrder;
import databaselibrary.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    public ArrayList<RestaurantOrder> getAllOrders(){
        ArrayList<RestaurantOrder> orders = new ArrayList<>();

        return null;
    }

    public void postOrder(RestaurantOrder order){

        orderRepository.save(order);

    }

}
