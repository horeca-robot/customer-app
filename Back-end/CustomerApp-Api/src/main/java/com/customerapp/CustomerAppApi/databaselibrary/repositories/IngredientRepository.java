package com.customerapp.CustomerAppApi.databaselibrary.repositories;

import com.customerapp.CustomerAppApi.databaselibrary.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * These repositories can be used like you would use any other {@link JpaRepository}.
 * See <a href="https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">https://docs.spring.io</a>
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Ingredient getIngredientByName(String name);

}
