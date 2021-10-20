package com.customerapp.CustomerAppApi.controllers;


import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import com.customerapp.CustomerAppApi.core.interfaces.IMockService;
import com.customerapp.CustomerAppApi.core.services.AdminMockService;
import databaselibrary.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryController {

    private ICategoryService categoryService;
    private IMockService mockService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IMockService mockService) {
        this.categoryService = categoryService;
        this.mockService = mockService;
    }

    @GetMapping("all")
    @CrossOrigin(origins = "*")
    public List<Category> getAllCategory(){
        mockService.Initialize();
        return categoryService.getAllCategory();
    }

    @GetMapping("byid")
    @CrossOrigin(origins = "*")
    public Category getCategoryById(@RequestBody String Id){
        //todo implement get all category by id func
        return null;
    }
}
