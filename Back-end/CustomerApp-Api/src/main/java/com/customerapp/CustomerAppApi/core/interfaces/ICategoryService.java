package com.customerapp.CustomerAppApi.core.interfaces;

import databaselibrary.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
}
