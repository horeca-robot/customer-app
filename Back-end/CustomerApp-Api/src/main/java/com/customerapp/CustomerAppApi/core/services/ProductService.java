package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import databaselibrary.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    public List<Product> getProducts() {
        List<Product> products =  new ArrayList<Product>();



        return null;
    }

    public Product getProductById(String id){
        return null;
    }

}


