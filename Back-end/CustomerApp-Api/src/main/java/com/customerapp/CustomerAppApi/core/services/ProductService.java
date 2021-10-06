package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IProductService;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products =  new ArrayList<Product>();

        products.add(new Product("bier","img",2,2,"Bieeeer", true, new Tag[0]));
        products.add(new Product("kaas","img",2,2,"kaasie is lekker", true, new Tag[0]));

        return products;
    }

    public Product getProductById(String id){
        return new Product("testvanid","image",2,2,"test voor ophalen product via id",true,new Tag[0]);
    }

}


