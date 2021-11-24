package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.core.services.AdminMockService;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import com.customerapp.CustomerAppApi.models.OrderDto;
import com.customerapp.CustomerAppApi.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order/")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<RestaurantOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Optional<RestaurantOrder> getOrderById(UUID id) {
        return orderService.getOrderById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Result postOrder(@RequestBody OrderDto order) {
        try {
            orderService.postOrder(order);
            return new Result(true, "Bestelling geplaatst");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<RestaurantOrder> getAllOrdersFromTable(@RequestBody int restaurantTable_id) {
        return orderService.getAllOrdersFromTable(restaurantTable_id);
    }

}
