package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import databaselibrary.models.Ingredient;
import databaselibrary.models.IngredientProduct;
import databaselibrary.models.Product;
import databaselibrary.repositories.IngredientProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IngredientProductService implements IIngredientProductService {

    private final IngredientProductRepository ingredientProductRepository;

    public IngredientProductService(IngredientProductRepository ingredientProductRepository) {
        this.ingredientProductRepository = ingredientProductRepository;
    }

    //GET
    public List<IngredientProduct> getAllIngredientProducts() {
        return ingredientProductRepository.findAll();
    }

    public List<Ingredient> getIngredientsByProductName(String name) {
        List<Ingredient> ingredients = new ArrayList<>();
        var ingredientProducts = ingredientProductRepository.findAll().stream().filter(i -> i.getProduct().getName().equals(name)).collect(Collectors.toList());
        for (IngredientProduct ingredientProduct : ingredientProducts) {
            ingredients.add(ingredientProduct.getIngredient());
        }
        return ingredients;
    }

    public Optional<IngredientProduct> getIngredientProductById(UUID id) {
        return ingredientProductRepository.findById(id);
    }
}
