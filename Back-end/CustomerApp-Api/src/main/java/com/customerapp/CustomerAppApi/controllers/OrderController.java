package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.models.ProductDto;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import com.customerapp.CustomerAppApi.models.PostOrderDto;
import com.customerapp.CustomerAppApi.models.Result;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order/")
public class OrderController {

    private final ModelMapper modelMapper;
    private final IOrderService orderService;

    @Autowired
    public OrderController(ModelMapper modelMapper, IOrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<RestaurantOrderDto> getAllOrders() {
        List<RestaurantOrder> orders = orderService.getAllOrders();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Optional<RestaurantOrderDto> getOrderById(@RequestParam UUID id) {
       var orderdto = convertToDTO(orderService.getOrderById(id).get());
       return Optional.ofNullable(orderdto);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Result postOrder(@RequestBody PostOrderDto order) {
        try {
            if(order.getNotes().length() > 350){
                return  new Result(false, "Uw notitie is te lang");
            }
            else {
                orderService.postOrder(order);
                return new Result(true, "Bestelling geplaatst");
            }
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("bytable")
    public List<RestaurantOrderDto> getAllOrdersFromTable(@RequestParam UUID restaurantTableId) {
        List<RestaurantOrder> orders = orderService.getAllOrdersFromTable(restaurantTableId);
        var orderlist = orders.stream().map(this::convertToDTO).collect(Collectors.toList());
        Collections.sort(orderlist);
        return orderlist;
    }

    private RestaurantOrder convertToEntity(RestaurantOrderDto restaurantOrderDto) {
        return modelMapper.map(restaurantOrderDto, RestaurantOrder.class);
    }

    private RestaurantOrderDto convertToDTO(RestaurantOrder restaurantOrder) {
        return modelMapper.map(restaurantOrder, RestaurantOrderDto.class);
    }

}
