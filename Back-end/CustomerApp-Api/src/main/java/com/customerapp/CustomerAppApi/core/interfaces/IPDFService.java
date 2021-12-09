package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.models.RestaurantOrderDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.Document;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface IPDFService {
    ResponseEntity<ByteArrayResource> createPDF(List<RestaurantOrderDto> restaurantOrdersDto, UUID restaurantTableId);
}
