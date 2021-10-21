package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.IngredientProduct;

import java.util.List;

public interface IIngredientProductService {
    List<IngredientProduct> getAllIngredientProducts();

}
