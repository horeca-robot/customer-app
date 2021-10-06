package com.customerapp.CustomerAppApi;

import com.customerapp.CustomerAppApi.Models.Category;
import com.customerapp.CustomerAppApi.Models.Ingredient;
import com.customerapp.CustomerAppApi.Models.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController {
    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public ArrayList<Category> getCategories(){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add((new Ingredient(1,"Tomaat")));
        ingredients.add((new Ingredient(2,"Kaas")));
        ingredients.add((new Ingredient(3,"Deeg")));

        ArrayList<Category> Categories = new ArrayList<>();
        Category preCategory = new Category(1,"Voorgerecht");
        preCategory.addProduct(new Product(4,"TomatenSoep",ingredients,5.00,"https://www.24kitchen.nl/files/styles/social_media_share/public/2020-08/verse-tomatensoep.jpg?itok=AqWg5Ju0"));
        Category mainCategory = new Category(1,"Hoofdgerecht");
        mainCategory.addProduct(new Product(2,"Pizza Margherita",ingredients,6.00,"https://pizzametpazzi.nl/wp-content/uploads/2017/02/pizzamargherita_orig-600x400.jpeg"));
        mainCategory.addProduct(new Product(1,"Pizza Quattro Formaggio",ingredients,7.00,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrzNx2-1thSmCe2JqsiTMun1yBerxXG3B2ng&usqp=CAU"));
        mainCategory.addProduct(new Product(3,"Pizza Gorgonzola",ingredients,8.80,"https://lekkeritaliaanskoken.nl/media/k2/items/cache/0c758c944f0062609b2677e28107bc5e_M.jpg"));
        Category postCategory = new Category(1,"Nagerecht");
        postCategory.addProduct(new Product(5,"Koffie",ingredients,2.00,"https://www.rtlnieuws.nl/sites/default/files/content/images/archive/70/koffie-smiley.jpg?itok=7EiqCwOh&width=1024&height=576&impolicy=semi_dynamic"));

        Categories.add(preCategory);
        Categories.add(mainCategory);
        Categories.add(postCategory);

        return Categories;

    };
}
