package com.customerapp.CustomerAppApi.core.services;

import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import edu.fontys.horecarobot.databaselibrary.repositories.CategoryRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminMockService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RestaurantTableRepository tableRepository;

    public AdminMockService(ProductRepository productRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, RestaurantTableRepository tableRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.tableRepository = tableRepository;
    }

    public void Initialize(){

        var hoofdgerecht = new Category();
        hoofdgerecht.setName("Hoofdgerecht");
        hoofdgerecht.setImage("https://www.dehoutenklaas.nl/wp-content/uploads/2016/12/hoofdgerecht.jpg");
        var products = new ArrayList<Product>(){

        };
        hoofdgerecht.setProducts(products);
        hoofdgerecht.setVisible(true);
        categoryRepository.save(hoofdgerecht);
    }

    public List<Product> getProductsOfCategory(String category)
    {
        List<Product> products = new ArrayList<>();
        switch (category)
        {
            case "Voorgerecht":
                Product tzatziki = new Product();
                tzatziki.setName("Tzatziki");
                tzatziki.setImage("https://img.static-rmg.be/a/food/image/q75/w640/h400/1086911/tzatziki.jpg");
                tzatziki.setPrice(4.50);
                tzatziki.setDescription("Griekse yoghurt met knoflook en komkommer");
                tzatziki.setContainsAlcohol(false);
                products.add(tzatziki);

                Product tamara = new Product();
                tamara.setName("Tamara");
                tamara.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Taramosalata01.jpg/230px-Taramosalata01.jpg");
                tamara.setPrice(5.00);
                tamara.setDescription("Viskruidsalade");
                tamara.setContainsAlcohol(false);
                products.add(tamara);

                Product tomatensoep = new Product();
                tomatensoep.setName("Tomatensoep");
                tomatensoep.setImage("https://www.matchusports.nl/wp-content/uploads/2020/09/Tomatensoep-880-x-495.jpg");
                tomatensoep.setPrice(4.75);
                tomatensoep.setDescription("lekker man");
                tomatensoep.setContainsAlcohol(false);
                products.add(tomatensoep);
            case "Hoofdgerecht":
                Product gyros = new Product();
                gyros.setName("Gyros");
                gyros.setImage("https://www.24kitchen.nl/files/styles/social_media_share/public/2021-05/gyros-hor.jpg?itok=2QDb5Hkd");
                gyros.setPrice(15.50);
                gyros.setDescription("Gesneden varkensvlees met tzatziki, mayonaise, frites en koolsalade");
                gyros.setContainsAlcohol(false);
                products.add(gyros);

                Product margherita = new Product();
                margherita.setName("Margherita");
                margherita.setImage("https://pizzametpazzi.nl/wp-content/uploads/2017/02/pizzamargherita_orig-600x400.jpeg");
                margherita.setPrice(5.00);
                margherita.setDescription("Pizza met tomaat en kaas");
                margherita.setContainsAlcohol(false);
                products.add(margherita);

                Product gorgonzola = new Product();
                gorgonzola.setName("Gorgonzola");
                gorgonzola.setImage("https://lekkeritaliaanskoken.nl/media/k2/items/cache/0c758c944f0062609b2677e28107bc5e_M.jpg");
                gorgonzola.setPrice(8.50);
                gorgonzola.setDescription("Pizza met tomaten, kaas en gorgonzola");
                gorgonzola.setContainsAlcohol(false);
                products.add(gorgonzola);
            case "Nagerecht":
                Product smurfijs = new Product();
                smurfijs.setName("Smurfijs");
                smurfijs.setImage("https://www.funfoodshop.nl/image/smurfijs_1.jpg?w=1200&h=1000&r=1");
                smurfijs.setPrice(2.00);
                smurfijs.setDescription("Blauw ijs en das mooi");
                smurfijs.setContainsAlcohol(false);
                products.add(smurfijs);

                Product cappuccino = new Product();
                cappuccino.setName("Cappuccino");
                cappuccino.setImage("https://www.jacobsdouweegbertsprofessional.nl/globalassets/cappuccino-maken/cappuccino-maken6v1.jpg?preset=two-halves-image-mobile");
                cappuccino.setPrice(2.75);
                cappuccino.setDescription("Koffie met créme laagje witte wel");
                cappuccino.setContainsAlcohol(false);
                products.add(cappuccino);

                Product kwatta = new Product();
                kwatta.setName("Kwatta");
                kwatta.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Chocolate02.png/520px-Chocolate02.png");
                kwatta.setPrice(3.00);
                kwatta.setDescription("Heerlijke chocolade blokjes");
                kwatta.setContainsAlcohol(false);
                products.add(kwatta);
        }
        return products;
    }


}