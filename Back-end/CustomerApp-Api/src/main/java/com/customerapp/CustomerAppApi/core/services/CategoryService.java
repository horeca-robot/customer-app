package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public List<Category> getAllParentCategories()
    {
        return categoryRepository.findAll().stream().filter(i->i.getChildCategories().size() > 0).collect(Collectors.toList());
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
