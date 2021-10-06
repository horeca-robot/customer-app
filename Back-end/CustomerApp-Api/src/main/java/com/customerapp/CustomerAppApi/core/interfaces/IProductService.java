package com.customerapp.CustomerAppApi.core.interfaces;

import edu.fontys.horecarobot.databaselibrary.models.Product;

import java.util.ArrayList;
import java.util.List;

public interface IProductService {
    public ArrayList<Product> getProducts();
    public Product getProductById(String id);
}
