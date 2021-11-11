package com.customerapp.CustomerAppApi.core.interfaces;

import com.customerapp.CustomerAppApi.databaselibrary.models.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(UUID id);
    Category getCategoryByName(String name);
}