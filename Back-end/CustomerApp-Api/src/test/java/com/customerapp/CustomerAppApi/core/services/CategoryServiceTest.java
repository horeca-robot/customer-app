package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockedItems;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.repositories.CategoryRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
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
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private MockedItems mockedItems = new MockedItems();

    @BeforeEach
    public void Init() {
        categoryService = new CategoryService(categoryRepository);
        MockitoAnnotations.openMocks(categoryRepository);
    }

    @Test
    public void getAllCategories() {
        var categories = mockedItems.getmockCategories();

        when(categoryRepository.findAll()).thenReturn(categories);

        var actual = categoryService.getAllCategories();

        Assertions.assertEquals(categories, actual);
    }

    @Test
    public void getCategoryById() {
        var category = mockedItems.getmockCategories().get(0);

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        var actual = categoryService.getCategoryById(category.getId());

        Assertions.assertEquals(category, actual);
    }

    @Test
    public void getCategoryByName() {
        var category = mockedItems.getmockCategories().get(0);

        when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);

        var actual = categoryService.getCategoryByName(category.getName());

        Assertions.assertEquals(category, actual);
    }
}