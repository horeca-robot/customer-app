package databaselibrary.repositories;

import databaselibrary.models.IngredientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientProductRepository extends JpaRepository<IngredientProduct, UUID> {
}
