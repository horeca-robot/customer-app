package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import org.w3c.dom.Document;

import java.util.List;

public interface IPDFService {
    Document createPDF(List<RestaurantOrderDto> restaurantOrdersDto);
}
