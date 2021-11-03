package com.customerapp.CustomerAppApi.databaselibrary.repositories;

import com.customerapp.CustomerAppApi.databaselibrary.models.IngredientProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientProductRepository extends JpaRepository<IngredientProduct, UUID> {

}
