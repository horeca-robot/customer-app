package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<Product> getProducts(){

        return productService.getProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Product getProductById(@RequestBody String Id){

        //todo implement get by id function
        return null;
    }


}
