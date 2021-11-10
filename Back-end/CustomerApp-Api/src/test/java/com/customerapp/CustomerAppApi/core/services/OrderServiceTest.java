package com.customerapp.CustomerAppApi.core.services;

import databaselibrary.enums.PaymentStatus;
import databaselibrary.models.Product;
import databaselibrary.models.ProductOrder;
import databaselibrary.models.RestaurantOrder;
import databaselibrary.models.RestaurantTable;
import databaselibrary.repositories.OrderRepository;
import databaselibrary.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void Init()
    {
        orderService = new OrderService(orderRepository);
        MockitoAnnotations.openMocks(orderService);
    }

}
