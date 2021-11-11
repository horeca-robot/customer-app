package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import com.customerapp.CustomerAppApi.databaselibrary.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Product getProductById(@RequestParam String id) {
        return productService.getProductById(UUID.fromString(id));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byname")
    public Product getProductByName(@RequestBody String name) {
        return productService.getProductByName(name);
    }

}
