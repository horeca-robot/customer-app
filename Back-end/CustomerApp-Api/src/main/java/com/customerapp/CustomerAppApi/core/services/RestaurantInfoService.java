package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IRestaurantInfoService;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantInfoService implements IRestaurantInfoService {
    private final RestaurantInfoRepository infoRepository;

    @Autowired
    public RestaurantInfoService(RestaurantInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public RestaurantInfo getRestaurantInfo() {
        var restaurantInfo = infoRepository.getInfo();
        RestaurantInfo exists = null;
        if (restaurantInfo.isPresent()) exists = restaurantInfo.get();
        return exists;
    }
}
