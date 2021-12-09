package com.customerapp.CustomerAppApi.models;

import lombok.Data;

import java.util.UUID;

@Data
public class BillProductDto {

    UUID productId;
    ProductDto product;
    int amount;

    public BillProductDto(ProductDto product, int amount){
        this.product = product;
        this.amount = amount;
        this.productId = product.getId();
    }
}
