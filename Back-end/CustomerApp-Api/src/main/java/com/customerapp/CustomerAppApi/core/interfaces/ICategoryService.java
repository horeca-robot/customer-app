package com.customerapp.CustomerAppApi.core.interfaces;

import edu.fontys.horecarobot.databaselibrary.models.Category;

import java.util.ArrayList;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
}
