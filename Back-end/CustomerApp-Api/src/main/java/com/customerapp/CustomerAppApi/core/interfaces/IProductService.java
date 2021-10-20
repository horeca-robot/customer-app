package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProducts();
    public Product getProductById(String id);
}
