package com.customerapp.CustomerAppApi.Models;

import java.awt.*;

public class Product {
    private int Id;
    private String Category;
    private String Name;
    private String[] Ingredients;
    private double Price;
    private String Img;

    public Product(int id,String category, String name,String[] ingredients, double price, String img)
    {
        Id = id;
        Category = category;
        Name = name;
        Ingredients = ingredients;
        Price = price;
        Img = img;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public String getCategory() {
        return Category;
    }

    public String[] getIngredients() {
        return Ingredients;
    }

    public String getImg() {
        return Img;
    }
}
