package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;
import databaselibrary.models.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ingredientproduct/")
public class IngredientProductController {

    private final IIngredientProductService ingredientProductService;

    @Autowired
    public IngredientProductController(IIngredientProductService ingredientProductService){
        this.ingredientProductService = ingredientProductService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<IngredientProduct> getAllIngredientProducts(){
        return ingredientProductService.getAllIngredientProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("product")
    public Optional<IngredientProduct> getIngredientProductById(@RequestParam String name){
        return ingredientProductService.getAllIngredientProducts().stream().filter(i->i.getProduct().getName().equals(name)).findFirst();
    }

}
