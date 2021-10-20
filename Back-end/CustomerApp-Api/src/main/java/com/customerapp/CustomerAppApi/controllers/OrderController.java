package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.core.services.AdminMockService;
import com.customerapp.CustomerAppApi.models.Result;
import databaselibrary.models.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private IOrderService orderService;


    @Autowired
    public OrderController(IOrderService orderService){
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public ArrayList<RestaurantOrder> getAllOrders(){
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Result postOrder(@RequestBody RestaurantOrder restaurantOrder){
        try{
            orderService.postOrder(restaurantOrder);
            return new Result(true, "Bestelling geplaatst");
        }
        catch(Exception e)
        {
            return new Result(false, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<RestaurantOrder> getOrderByTable(/*Todo add @Request parameters */){
        // todo get orders from database
        return new ArrayList<RestaurantOrder>(){
            {

            }
        };
    }

}
