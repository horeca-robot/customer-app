package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import databaselibrary.models.Category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    public List<Category> getAllCategory(){
        List<Category> categories =  new ArrayList<Category>();

        return null;
    }


}
