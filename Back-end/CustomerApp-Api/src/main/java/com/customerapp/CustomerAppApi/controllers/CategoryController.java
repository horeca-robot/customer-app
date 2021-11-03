package com.customerapp.CustomerAppApi.controllers;


import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import com.customerapp.CustomerAppApi.databaselibrary.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    @CrossOrigin(origins = "*")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @GetMapping("byid")
    @CrossOrigin(origins = "*")
    public Category getCategoryById(@RequestParam String id){
        return categoryService.getCategoryById(UUID.fromString(id));
    }

    @GetMapping("byname")
    @CrossOrigin(origins = "*")
    public Category getCategoryByName(@RequestBody String name){
        return categoryService.getCategoryByName(name);
    }
}
