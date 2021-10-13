package com.customerapp.CustomerAppApi.Models;

import java.util.ArrayList;

public class Category {
    private int Id;
    private String Name;
    private String Img;
    private ArrayList<Product> Products = new ArrayList<>();

    public Category(int id, String name, String img) {
        Id = id;
        Name = name;
        Img = img;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getImg() {
        return Img;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void addProduct(Product product)
    {
        Products.add(product);
    }
}
