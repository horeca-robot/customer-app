package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;
import databaselibrary.repositories.IngredientProductRepository;
import databaselibrary.repositories.OrderRepository;
import databaselibrary.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.getById(id);
    }

    public Product getProductByName(String name){return productRepository.getProductByName(name);}

}


