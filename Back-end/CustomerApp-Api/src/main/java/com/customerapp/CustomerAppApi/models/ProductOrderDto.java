package com.customerapp.CustomerAppApi.models;

import edu.fontys.horecarobot.databaselibrary.enums.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ProductOrderDto {
    private UUID id;
    private OrderStatus orderStatus;
    private ProductDto product;
    private List<ProductDto> byProducts = new ArrayList<>();
}
