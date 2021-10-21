package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import com.customerapp.CustomerAppApi.core.interfaces.IIngredientProductService;
import com.customerapp.CustomerAppApi.core.interfaces.IMockService;
import databaselibrary.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mock/")
public class MockController {

    private final IMockService mockService;
    private final ICategoryService categoryService;

    @Autowired
    public MockController(IMockService mockService, ICategoryService categoryService){
        this.mockService = mockService;
        this.categoryService = categoryService;
    }

    @GetMapping("initialize")
    @CrossOrigin(origins = "*")
    public List<Category> getAllCategory(){
        mockService.Initialize();
        return categoryService.getAllCategories();
    }
}
