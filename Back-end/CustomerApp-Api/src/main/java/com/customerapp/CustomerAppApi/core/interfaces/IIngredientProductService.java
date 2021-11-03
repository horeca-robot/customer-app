package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.databaselibrary.models.Ingredient;
import com.customerapp.CustomerAppApi.databaselibrary.models.IngredientProduct;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IIngredientProductService {
    List<IngredientProduct> getAllIngredientProducts();
    List<Ingredient> getIngredientsByProductName(String name);
    Optional<IngredientProduct> getIngredientProductById(UUID id);
}
