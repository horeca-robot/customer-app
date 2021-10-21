package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    Product getProductByName(String name);
}
