package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import edu.fontys.horecarobot.databaselibrary.models.Ingredient;
import edu.fontys.horecarobot.databaselibrary.models.IngredientProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ingredientproduct/")
public class IngredientProductController {

    private final IIngredientProductService ingredientProductService;

    @Autowired
    public IngredientProductController(IIngredientProductService ingredientProductService) {
        this.ingredientProductService = ingredientProductService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<IngredientProduct> getAllIngredientProducts() {
        return ingredientProductService.getAllIngredientProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byname")
    public List<Ingredient> getIngredientsOfProduct(@RequestBody String name) {
        return ingredientProductService.getIngredientsByProductName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byid")
    public Optional<IngredientProduct> getIngredientProductById(@RequestBody UUID id) {
        return ingredientProductService.getIngredientProductById(id);
    }

}
