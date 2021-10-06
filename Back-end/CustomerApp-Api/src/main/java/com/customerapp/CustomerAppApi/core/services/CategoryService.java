package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ICategoryService;
import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categories =  new ArrayList<Category>();
        categories.add(new Category("Voorgerecht","image",null,true,new Product[0]));
        categories.add(new Category("Hoofdgerecht","image",null,true,new Product[0]));
        categories.add(new Category("Nagerecht","image",null,true,new Product[0]));
        return categories;
    }


}
