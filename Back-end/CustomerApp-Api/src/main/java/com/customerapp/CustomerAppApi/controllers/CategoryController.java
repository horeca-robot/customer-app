package com.customerapp.CustomerAppApi.controllers;


import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        return categoryService.getAllCategory();
    }

    @GetMapping("byid")
    @CrossOrigin(origins = "*")
    public Category getCategoryById(@RequestBody String Id){
        //todo implement get all category by id func
        return null;
    }
}
