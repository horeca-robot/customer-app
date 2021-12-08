package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import org.w3c.dom.Document;

import java.util.List;
import java.util.UUID;

public interface IPDFService {
    Document createPDF(List<RestaurantOrderDto> restaurantOrdersDto, UUID restaurantTableId);
}
