package com.customerapp.CustomerAppApi.core.services;


import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private List<Product> mockProducts;

    @BeforeEach
    public void Init() {
        productService = new ProductService(productRepository);
        MockitoAnnotations.openMocks(productService);
    }

    //Requirements tests
    @Test
    public void Test_Get_All_Products_From_Menu()
    {
        mockProducts = getMockProducts();

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> actual = productService.getAllProducts();

        Assertions.assertEquals(mockProducts,actual);
    }

    //Test Cases
    @Test
    public void TC01_Select_A_Product_From_Products() {
        mockProducts = getMockProducts();

        String productName = "Fanta";

        Product expected = mockProducts.stream().filter(i -> i.getName().equals(productName)).findAny().get();

        when(productRepository.getProductByName(productName)).thenReturn(expected);

        Product actual = productService.getProductByName(productName);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TC02_View_Empty_List_Of_Products() {
        mockProducts = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(mockProducts);

        Assertions.assertEquals(productService.getAllProducts().size(), mockProducts.size());
    }

    private List<Product> getMockProducts() {
        List<Product> products = new ArrayList<>();

        Product cola = new Product();
        cola.setName("Cola");
        cola.setPrice(2.50);
        cola.setContainsAlcohol(false);
        products.add(cola);

        Product fanta = new Product();
        fanta.setName("Fanta");
        fanta.setPrice(2.50);
        fanta.setContainsAlcohol(false);
        products.add(fanta);

        Product sprite = new Product();
        sprite.setName("Sprite");
        sprite.setPrice(2.50);
        sprite.setContainsAlcohol(false);
        products.add(sprite);

        return products;
    }
}
