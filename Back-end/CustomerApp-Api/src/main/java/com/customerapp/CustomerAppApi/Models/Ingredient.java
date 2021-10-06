package com.customerapp.CustomerAppApi.Models;

public class Ingredient {
    private int Id;
    private String Name;

    public Ingredient(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
