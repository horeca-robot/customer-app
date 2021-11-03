package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import com.customerapp.CustomerAppApi.databaselibrary.models.Category;
import com.customerapp.CustomerAppApi.databaselibrary.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id)
    {
        return categoryRepository.findById(id).get();
    }

    public Category getCategoryByName(String name)
    {
        return categoryRepository.getCategoryByName(name);
    }

}
