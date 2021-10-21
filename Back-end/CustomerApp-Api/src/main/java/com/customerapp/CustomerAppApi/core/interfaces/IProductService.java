package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    public List<Product> getProducts();
    public Product getProductById(UUID id);
}
