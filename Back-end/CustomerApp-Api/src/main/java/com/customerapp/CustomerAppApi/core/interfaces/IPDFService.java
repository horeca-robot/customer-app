package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import com.itextpdf.text.Document;

import java.util.List;

public interface IPDFService {
    Document createPDF(List<RestaurantOrderDto> restaurantOrdersDto);
}
