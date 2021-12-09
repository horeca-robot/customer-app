package com.customerapp.CustomerAppApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.fontys.horecarobot.databaselibrary.models.ProductOrder;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantTable;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class RestaurantOrderDto implements Comparable<RestaurantOrderDto> {
    private UUID id;
    private double subTotal;
    private boolean paid;
    private Date createdAt;
    private RestaurantTable table;
    private List<ProductOrderDto> productOrders = new ArrayList<>();

    @Override
    public int compareTo(@NotNull RestaurantOrderDto o) {
        return createdAt.compareTo(o.createdAt);
    }
}
