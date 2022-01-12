package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantInfo;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PDFServiceTest {
    @InjectMocks
    private PDFService sut;

    @Mock
    private RestaurantInfoService infoService;

    @Mock
    private TableService tableService;

    @BeforeEach
    void setUp() {
        sut = new PDFService(infoService, tableService);
        MockitoAnnotations.openMocks(sut);
    }

    @Test
    void createPDFReturnsCorrectResponse() {
        // Arrange
        var restaurantInfo = mock(RestaurantInfo.class);
        UUID uuid = UUID.randomUUID();
        RestaurantTable restaurantTable = mock(RestaurantTable.class);
        Optional<RestaurantTable> optionalTable = Optional.of(restaurantTable);
        List<RestaurantOrderDto> orderList = new ArrayList<>();

        when(infoService.getRestaurantInfo()).thenReturn(restaurantInfo);
        when(restaurantInfo.getPrimaryColor()).thenReturn("#82C4B5");
        when(restaurantInfo.getName()).thenReturn("PizzaHut");
        when(restaurantInfo.getId()).thenReturn(uuid);
        when(tableService.getRestaurantTableById(uuid)).thenReturn(optionalTable);
        when(restaurantTable.getTableNumber()).thenReturn(1);
        when(restaurantInfo.getRestaurantLogo()).thenReturn("url");
        when(restaurantInfo.getContactPersonName()).thenReturn("Bob Marley");
        when(restaurantInfo.getContactPersonPhone()).thenReturn("+31 07373784");
        when(restaurantInfo.getContactPersonEmail()).thenReturn("bob.marley@gmail.com");
        when(restaurantInfo.getAddress()).thenReturn("Dorpsstraat 123");
        when(restaurantInfo.getCity()).thenReturn("Eindhoven");
        when(restaurantInfo.getCountry()).thenReturn("Nederland");
        when(restaurantInfo.getAccountNumber()).thenReturn("NL52ABNA1234567");
        when(restaurantInfo.getPostalCode()).thenReturn("1234 AA");

        // Act
        var actual = sut.createPDF(orderList, uuid);

        // Assert
        Assertions.assertEquals(actual.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(actual.getClass(), ResponseEntity.class);
    }

    @Test
    void createPDFReturnsNull() {
        try {
            // Arrange
            UUID uuid = UUID.randomUUID();
            List<RestaurantOrderDto> orderList = new ArrayList<>();

            // Act
            sut.createPDF(orderList, uuid);

            // Assert fail
            fail();
        } catch (Exception e) {
            // Assert success
            then(e).isInstanceOf(NullPointerException.class);
        }
    }
}
