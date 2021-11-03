package com.customerapp.CustomerAppApi.models;

import com.customerapp.CustomerAppApi.databaselibrary.models.Product;

import java.util.List;

public class OrderDto {

    private List<Product> products;
    private double subTotal;
    private int tableNumber;
    private String notes;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
