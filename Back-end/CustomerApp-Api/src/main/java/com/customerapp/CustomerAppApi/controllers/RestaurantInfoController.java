package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
