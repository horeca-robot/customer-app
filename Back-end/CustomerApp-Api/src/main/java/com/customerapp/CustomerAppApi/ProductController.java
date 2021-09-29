package com.customerapp.CustomerAppApi;

import com.customerapp.CustomerAppApi.Models.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController {
    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public ArrayList<Product> getProducts(){
        String[] ingredients = {""};
        return  new ArrayList<Product>(){
            {
                add(new Product(1,"Pizza","Stinkvis",ingredients,7.00,"https://picsum.photos/400/200"));
                add(new Product(2,"Pizza","Stinkkaas",ingredients,6.00,"https://picsum.photos/400/200"));
                add(new Product(3,"Pizza","StinkTomaat",ingredients,8.80,"https://picsum.photos/400/200"));
            }
        };
    }
}
