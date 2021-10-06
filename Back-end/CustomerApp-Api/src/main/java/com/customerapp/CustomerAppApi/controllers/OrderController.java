package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import com.customerapp.CustomerAppApi.models.Result;
import edu.fontys.horecarobot.databaselibrary.models.Order;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public ArrayList<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public Result postOrder(@RequestBody Order order){
        // todo post order to database
        try{
            orderService.postOrder(order);
            return new Result(true, "Order geplaatst!");
        }
        catch(Exception e){
            return new Result(false, "Order plaatsing mislukt");
        }


    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ArrayList<Order> getOrderByTable(/*Todo add @Request parameters */){
        // todo get orders from database
        return new ArrayList<Order>(){
            {

            }
        };
    }

}
