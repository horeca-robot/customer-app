package com.customerapp.CustomerAppApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.IngredientProduct;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private String image;
    private double price;
    private double discountPrice;
    private String description;
    private boolean containsAlcohol;
    private List<Tag> tags = new ArrayList<>();

    @JsonIgnoreProperties("product")
    private List<IngredientProduct> ingredients;

    @JsonIgnoreProperties({"products","childCategories","parentCategories"})
    private List<Category> categories = new ArrayList<>();
}
