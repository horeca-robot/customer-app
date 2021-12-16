package com.customerapp.CustomerAppApi.models;

import edu.fontys.horecarobot.databaselibrary.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostOrderDto {

    private List<Product> products = new ArrayList<>();
    private UUID tableId;
    private String notes;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public UUID getTableId() {
        return tableId;
    }

    public void setTableId(UUID tableNumber) {
        this.tableId = tableNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
