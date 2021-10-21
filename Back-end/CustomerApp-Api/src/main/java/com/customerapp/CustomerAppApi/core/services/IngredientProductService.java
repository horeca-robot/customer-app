package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import databaselibrary.models.IngredientProduct;
import databaselibrary.repositories.IngredientProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IngredientProductService implements IIngredientProductService {

    private final IngredientProductRepository ingredientProductRepository;

    public IngredientProductService(IngredientProductRepository ingredientProductRepository)
    {
        this.ingredientProductRepository = ingredientProductRepository;
    }

    public List<IngredientProduct> getAllIngredientProducts()
    {
        return ingredientProductRepository.findAll();
    }
}
