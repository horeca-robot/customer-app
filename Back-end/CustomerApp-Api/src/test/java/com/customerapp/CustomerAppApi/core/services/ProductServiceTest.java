package com.customerapp.CustomerAppApi.core.services;


import com.customerapp.CustomerAppApi.core.MockFactory;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private MockFactory mockFactory = new MockFactory();

    @BeforeEach
    public void Init() {
        productService = new ProductService(productRepository);
        MockitoAnnotations.openMocks(productService);
    }

    //Test Cases
    @Test
    public void TC01_Select_A_Product_From_Products() {
        var mockProducts = mockFactory.getMockProducts();

        String productName = "Fanta";

        Product expected = mockProducts.stream().filter(i -> i.getName().equals(productName)).findAny().get();

        when(productRepository.getProductByName(productName)).thenReturn(expected);

        Product actual = productService.getProductByName(productName);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TC02_View_Empty_List_Of_Products() {
        List<Product> mockProducts = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(mockProducts);

        Assertions.assertEquals(productService.getAllProducts(), mockProducts);
    }

    @Test
    public void getAllProducts() {
        var mockProducts = mockFactory.getMockProducts();

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> actual = productService.getAllProducts();

        Assertions.assertEquals(mockProducts, actual);
    }

    @Test
    public void getProductById() {
        var mockProduct = mockFactory.getMockProducts().get(0);

        when(productRepository.findById(mockProduct.getId())).thenReturn(Optional.of(mockProduct));

        var actual = productService.getProductById(mockProduct.getId());

        Assertions.assertEquals(mockProduct, actual);
    }

    @Test
    public void getProductByName() {
        var mockProduct = mockFactory.getMockProducts().get(0);

        when(productRepository.getProductByName(mockProduct.getName())).thenReturn(mockProduct);

        var actual = productService.getProductByName(mockProduct.getName());

        Assertions.assertEquals(mockProduct, actual);
    }
}
