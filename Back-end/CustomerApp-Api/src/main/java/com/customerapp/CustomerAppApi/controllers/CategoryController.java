package com.customerapp.CustomerAppApi.controllers;


import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import com.customerapp.CustomerAppApi.models.CategoryDto;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryController {

    private final ModelMapper modelMapper;
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ModelMapper modelMapper, ICategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    @CrossOrigin(origins = "*")
    public List<CategoryDto> getAllCategory(){
        List<Category> categories = categoryService.getAllCategories();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("all/parent")
    @CrossOrigin(origins = "*")
    public List<CategoryDto> getAllParentCategories(){
        List<Category> categories = categoryService.getAllParentCategories();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("all/child")
    @CrossOrigin(origins = "*")
    public List<CategoryDto> getAllChildCategories(){
        List<Category> categories = categoryService.getAllParentCategories();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("byid")
    @CrossOrigin(origins = "*")
    public CategoryDto getCategoryById(@RequestParam String id){
        return convertToDTO(categoryService.getCategoryById(UUID.fromString(id)));
    }

    @GetMapping("byname")
    @CrossOrigin(origins = "*")
    public CategoryDto getCategoryByName(@RequestBody String name){
        return convertToDTO(categoryService.getCategoryByName(name));
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
    private CategoryDto convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
