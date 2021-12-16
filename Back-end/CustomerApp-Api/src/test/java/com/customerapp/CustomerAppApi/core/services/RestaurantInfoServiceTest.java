package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockFactory;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantInfoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantInfoServiceTest {

    @InjectMocks
    private RestaurantInfoService restaurantInfoService;

    @Mock
    private RestaurantInfoRepository restaurantInfoRepository;

    private MockFactory mockFactory = new MockFactory();

    @BeforeEach
    public void Init() {
        restaurantInfoService = new RestaurantInfoService(restaurantInfoRepository);
        MockitoAnnotations.openMocks(restaurantInfoService);
    }

    @Test
    public void getRestaurantInfo() {
        var restaurantInfo = mockFactory.getmockRestaurantInfo();

        when(restaurantInfoRepository.getInfo()).thenReturn(Optional.of(restaurantInfo));

        var actual = restaurantInfoService.getRestaurantInfo();

        Assertions.assertEquals(restaurantInfo,actual);
    }
}