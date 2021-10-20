package com.customerapp.CustomerAppApi.core.services;

import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import edu.fontys.horecarobot.databaselibrary.repositories.CategoryRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AdminMockService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RestaurantTableRepository tableRepository;

    public AdminMockService(ProductRepository productRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, RestaurantTableRepository tableRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.tableRepository = tableRepository;
    }

    public void Initialize(){

        var hoofdgerecht = new Category();
        hoofdgerecht.setName("Hoofdgerecht");
        hoofdgerecht.setImage("https://www.dehoutenklaas.nl/wp-content/uploads/2016/12/hoofdgerecht.jpg");
        var products = new ArrayList<Product>(){{

        }};
        hoofdgerecht.setProducts(products);
        hoofdgerecht.setVisible(true);
        categoryRepository.save(hoofdgerecht);
    }


}