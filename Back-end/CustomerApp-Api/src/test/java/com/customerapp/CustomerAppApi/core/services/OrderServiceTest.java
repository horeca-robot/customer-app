package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockFactory;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductOrderRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantOrderRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private RestaurantOrderRepository orderRepository;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private RestaurantTableRepository tableRepository;

    private final MockFactory mockFactory = new MockFactory();

    @BeforeEach
    public void Init() {
        orderService = new OrderService(orderRepository, tableRepository, productOrderRepository);
        MockitoAnnotations.openMocks(orderService);
    }

    @Test
    public void getAllOrders() {
        var mockOrders = mockFactory.getmockRestaurantOrders();

        when(orderRepository.findAll()).thenReturn(mockOrders);

        var actual = orderService.getAllOrders();

        Assertions.assertEquals(mockOrders, actual);
    }

    @Test
    public void getOrderById() {
        var mockOrder = mockFactory.getmockRestaurantOrders().get(0);

        when(orderRepository.findById(mockOrder.getId())).thenReturn(Optional.of(mockOrder));

        var actual = orderService.getOrderById(mockOrder.getId());

        Assertions.assertEquals(mockOrder, actual.get());
    }

    @Test
    public void getAllOrdersFromTable() {
        var mockTable = mockFactory.getMockRestaurantTable();
        var mockOrders = mockFactory.getmockRestaurantOrders();

        when(orderRepository.getByTableId(mockTable.getId())).thenReturn(mockOrders);

        var actual = orderService.getAllOrdersFromTable(mockTable.getId());

        Assertions.assertEquals(mockOrders, actual);
    }
}
