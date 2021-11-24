package com.customerapp.CustomerAppApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import lombok.Data;


import java.util.List;
import java.util.UUID;

@Data
public class CategoryDto {
    private UUID id;
    private String name;
    private String image;

    @JsonIgnoreProperties("product")
    private List<Category> parentCategories;

    @JsonIgnoreProperties("products")
    private List<Category> childCategories;

    private boolean visible;

    @JsonIgnoreProperties("categories")
    private List<Product> products;



}
