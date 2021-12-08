package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/info/")
public class RestaurantInfoController {
    private final IRestaurantInfoService restaurantInfoService;

    @Autowired
    public RestaurantInfoController(IRestaurantInfoService restaurantInfoService){
        this.restaurantInfoService = restaurantInfoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public RestaurantInfo getAllOrders() {
        return restaurantInfoService.getRestaurantInfo();
    }
}
