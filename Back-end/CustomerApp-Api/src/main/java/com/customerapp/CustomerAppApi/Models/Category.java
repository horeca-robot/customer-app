package com.customerapp.CustomerAppApi.Models;

import java.util.ArrayList;

public class Category {
    private int Id;
    private String Name;
    private ArrayList<Product> Products = new ArrayList<>();

    public Category(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void addProduct(Product product)
    {
        Products.add(product);
    }
}
