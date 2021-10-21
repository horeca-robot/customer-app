package databaselibrary.repositories;

import databaselibrary.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * These repositories can be used like you would use any other {@link JpaRepository}.
 * See <a href="https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">https://docs.spring.io</a>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product getProductByName(String name);
    Product getProductById(UUID id);
}
