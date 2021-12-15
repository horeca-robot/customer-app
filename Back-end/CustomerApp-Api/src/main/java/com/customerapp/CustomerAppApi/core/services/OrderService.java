package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.models.PostOrderDto;
import edu.fontys.horecarobot.databaselibrary.enums.OrderStatus;
import edu.fontys.horecarobot.databaselibrary.models.ProductOrder;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantOrderRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService implements IOrderService {

    private final RestaurantOrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;

    public OrderService(RestaurantOrderRepository orderRepository, RestaurantTableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    //GET
    public List<RestaurantOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<RestaurantOrder> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<RestaurantOrder> getAllOrdersFromTable(UUID restaurantTableId) {
        return orderRepository.getByTableId(restaurantTableId);
    }

    //POST
    public void postOrder(@NotNull PostOrderDto order) {
        var restaurantOrder = new RestaurantOrder();
        restaurantOrder.setTable(tableRepository.findById(order.getTableId()).get());
        restaurantOrder.setPaid(false);
        restaurantOrder.setNote(order.getNotes());
        restaurantOrder.setCreatedAt(new Date());
        double subTotal = 0;
        for (var product : order.getProducts()) {
            subTotal += product.getPrice();
        }
        restaurantOrder.setSubTotal(subTotal);
        List<ProductOrder> productOrders = new ArrayList<>();

        for (var product : order.getProducts()) {
            var productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);
            productOrders.add(productOrder);
        }

        restaurantOrder.setProductOrders(productOrders);
        orderRepository.saveAndFlush(restaurantOrder);

    }
}
