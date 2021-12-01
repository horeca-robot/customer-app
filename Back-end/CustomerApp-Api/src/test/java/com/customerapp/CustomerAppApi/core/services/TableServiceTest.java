package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockedItems;
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
public class TableServiceTest {

    @InjectMocks
    private TableService tableService;

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    private MockedItems mockedItems = new MockedItems();

    @BeforeEach
    public void Init() {
        tableService = new TableService(restaurantTableRepository);
        MockitoAnnotations.openMocks(restaurantTableRepository);
    }


    @Test
    public void getAllRestaurantTables() {
        var restaurantTables = mockedItems.getMockRestaurantTableList();

        when(restaurantTableRepository.findAll()).thenReturn(restaurantTables);

        var actual = tableService.getAllRestaurantTables();

        Assertions.assertEquals(restaurantTables, actual);
    }

    @Test
    public void getRestaurantTableById() {
        var restaurantTable = mockedItems.getMockRestaurantTable();

        when(restaurantTableRepository.findById(restaurantTable.getId())).thenReturn(Optional.of(restaurantTable));

        var actual = tableService.getRestaurantTableById(restaurantTable.getId());

        Assertions.assertEquals(restaurantTable, actual.get());
    }
}