package com.customerapp.CustomerAppApi.core.services;

import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantOrderRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private RestaurantOrderRepository orderRepository;



}
