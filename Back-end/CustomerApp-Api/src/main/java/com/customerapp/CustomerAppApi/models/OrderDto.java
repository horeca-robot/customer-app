package com.customerapp.CustomerAppApi.models;

import com.customerapp.CustomerAppApi.databaselibrary.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDto {

    private List<Product> products = new ArrayList<>();
    private UUID tableNumber;
    private String notes;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public UUID getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(UUID tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
