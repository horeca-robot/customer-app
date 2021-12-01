package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockedItems;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductOrderRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantOrderRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientProductServiceTest {

    @InjectMocks
    private IngredientProductService ingredientProductService;

    @Mock
    private IngredientProductRepository ingredientProductRepository;

    private final MockedItems mockedItems = new MockedItems();

    @BeforeEach
    public void Init() {
        ingredientProductService = new IngredientProductService(ingredientProductRepository);
        MockitoAnnotations.openMocks(ingredientProductRepository);
    }

    @Test
    public void getAllIngredientProducts() {
        var ingredientProducts = mockedItems.getmockIngredientProducts();

        when(ingredientProductRepository.findAll()).thenReturn(ingredientProducts);

        var actual = ingredientProductService.getAllIngredientProducts();

        Assertions.assertEquals(ingredientProducts, actual);
    }

    @Test
    public void getIngredientsByProductName() {
        var ingredientProducts = mockedItems.getmockIngredientProducts();

        when(ingredientProductRepository.findByProductName(ingredientProducts.get(0).getProduct().getName())).thenReturn(ingredientProducts);

        var actual = ingredientProductService.getIngredientsByProductName(ingredientProducts.get(0).getProduct().getName());

        Assertions.assertEquals(ingredientProducts.get(0).getIngredient(), actual.get(0));
    }

    @Test
    public void getIngredientProductById() {
        var ingredientProduct = mockedItems.getmockIngredientProducts().get(0);

        when(ingredientProductRepository.findById(ingredientProduct.getId())).thenReturn(Optional.of(ingredientProduct));

        var actual = ingredientProductService.getIngredientProductById(ingredientProduct.getId());

        Assertions.assertEquals(ingredientProduct, actual.get());
    }
}