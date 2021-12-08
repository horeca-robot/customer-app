package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import edu.fontys.horecarobot.databaselibrary.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient/")
public class IngredientController {

    private final IIngredientProductService ingredientProductService;

    @Autowired
    public IngredientController(IIngredientProductService ingredientProductService) {
        this.ingredientProductService = ingredientProductService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("byproductid")
    public List<Ingredient> GetIngredientsByProductId(@RequestParam String id){
        var list = ingredientProductService.getAllIngredientProducts();
        var Ingredients = new ArrayList<Ingredient>();
        for(var ingredientProduct: list){
            if(ingredientProduct.getProduct().getId().toString().equals(id)){
                Ingredients.add(ingredientProduct.getIngredient());
            }
        }
        return Ingredients;
    }
}
