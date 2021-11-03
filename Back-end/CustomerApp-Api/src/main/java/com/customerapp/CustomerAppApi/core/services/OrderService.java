package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.databaselibrary.enums.OrderStatus;
import com.customerapp.CustomerAppApi.databaselibrary.models.Product;
import com.customerapp.CustomerAppApi.databaselibrary.models.ProductOrder;
import com.customerapp.CustomerAppApi.databaselibrary.models.RestaurantOrder;
import com.customerapp.CustomerAppApi.databaselibrary.repositories.OrderRepository;
import com.customerapp.CustomerAppApi.databaselibrary.repositories.ProductOrderRepository;
import com.customerapp.CustomerAppApi.databaselibrary.repositories.RestaurantTableRepository;
import com.customerapp.CustomerAppApi.models.OrderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final RestaurantTableRepository tableRepository;

    public OrderService(OrderRepository orderRepository, RestaurantTableRepository tableRepository, ProductOrderRepository productOrderRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productOrderRepository = productOrderRepository;
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
    public void postOrder(OrderDto order) {
        var restaurantOrder = new RestaurantOrder();
        restaurantOrder.setTable(tableRepository.findRestaurantTableByTableNumber(order.getTableNumber()));
        restaurantOrder.setPayed(false);
        restaurantOrder.setSubTotal(order.getSubTotal());
        orderRepository.saveAndFlush(restaurantOrder);
        List<ProductOrder> productOrders = new ArrayList<>();

        for(var product : order.getProducts()){
            var productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);
            productOrders.add(productOrder);
            productOrderRepository.saveAndFlush(productOrder);
        }

        restaurantOrder.setProductOrders(productOrders);



    }

}
