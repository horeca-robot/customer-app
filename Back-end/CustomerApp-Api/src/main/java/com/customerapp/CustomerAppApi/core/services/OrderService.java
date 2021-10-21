package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import databaselibrary.models.RestaurantOrder;
import databaselibrary.models.RestaurantTable;
import databaselibrary.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //GET
    public List<RestaurantOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<RestaurantOrder> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public List<RestaurantOrder> getAllOrdersFromTable(int restaurantTable_number)
    {
        return orderRepository.findAll().stream().filter(i->i.getTable().getTableNumber() == restaurantTable_number).collect(Collectors.toList());
    }

    //POST
    public void postOrder(RestaurantOrder order) {
        orderRepository.saveAndFlush(order);
    }

}
