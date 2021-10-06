package com.customerapp.CustomerAppApi.Models;

import java.awt.*;
import java.util.ArrayList;

public class Product {
    private int Id;
    private String Name;
    private ArrayList<Ingredient> Ingredients;
    private double Price;
    private String Img;

    public Product(int id, String name, ArrayList<Ingredient> ingredients, double price, String img)
    {
        Id = id;
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

    public ArrayList<Ingredient> getIngredients() {
        return Ingredients;
    }

    public String getImg() {
        return Img;
    }
}
