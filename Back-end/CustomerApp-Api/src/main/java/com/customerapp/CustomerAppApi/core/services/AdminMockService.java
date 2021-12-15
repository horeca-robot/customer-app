package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IMockService;
import edu.fontys.horecarobot.databaselibrary.models.*;
import edu.fontys.horecarobot.databaselibrary.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMockService implements IMockService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RestaurantTableRepository tableRepository;
    private final IngredientProductRepository ingredientProductRepository;
    private final TagRepository tagRepository;
    private final RestaurantInfoRepository restaurantInfoRepository;

    public AdminMockService(ProductRepository productRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, RestaurantTableRepository tableRepository, IngredientProductRepository ingredientProductRepository, TagRepository tagRepository, RestaurantInfoRepository restaurantInfoRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.tableRepository = tableRepository;
        this.ingredientProductRepository = ingredientProductRepository;
        this.tagRepository = tagRepository;
        this.restaurantInfoRepository = restaurantInfoRepository;
    }

    public void Initialize() {

        setIngredients();
        setTags();

        List<Category> voorgerecht_child_list = new ArrayList<>();
        List<Category> hoofdgerecht_child_list = new ArrayList<>();
        List<Category> nagerecht_child_list = new ArrayList<>();

        var info = new RestaurantInfo();
        info.setName("Syan");
        info.setClosingTime(LocalTime.MIDNIGHT);
        info.setOpeningTime(LocalTime.NOON);
        info.setContactPersonEmail("info@syan.com");
        info.setContactPersonName("Jan-pieter");
        info.setContactPersonPhone("0612345678");
        info.setPrimaryColor("#82C4B5");
        info.setSecondaryColor("#BDAD89");
        info.setRestaurantLogo("https://e7.pngegg.com/pngimages/332/214/png-clipart-restaurant-name-logo-layout-illustration-logo-restaurant-food-food-logo-design-food-text.png");
        restaurantInfoRepository.saveAndFlush(info);


        var salade = new Category();
        salade.setName("Salade");
        salade.setImage("https://www.culy.nl/wp-content/uploads/2013/03/Salade-met-dadels-amandelen-en-gekarameliseerde-rode-ui1.jpg");
        salade.setVisible(true);
        salade.setProducts(getProductsOfCategory(salade.getName()));
        voorgerecht_child_list.add(salade);
        categoryRepository.saveAndFlush(salade);


        var yoghurt = new Category();
        yoghurt.setName("Yoghurt");
        yoghurt.setImage("https://www.24kitchen.nl/files/styles/social_media_share/public/2019-08/shutterstock_229091863.jpg?itok=fcLC88M5");
        yoghurt.setVisible(true);
        yoghurt.setProducts(getProductsOfCategory(yoghurt.getName()));
        voorgerecht_child_list.add(yoghurt);
        categoryRepository.saveAndFlush(yoghurt);



        var soep = new Category();
        soep.setName("Soep");
        soep.setImage("https://itsnotaboutcooking.nl/wp-content/uploads/2021/02/Soep-Marrokaans4-scaled.jpg");
        soep.setVisible(true);
        soep.setProducts(getProductsOfCategory(soep.getName()));
        voorgerecht_child_list.add(soep);
        categoryRepository.saveAndFlush(soep);



        var grill = new Category();
        grill.setName("Grill");
        grill.setImage("https://media.s-bol.com/mZQWBpXznL5G/550x366.jpg");
        grill.setVisible(true);
        grill.setProducts(getProductsOfCategory(grill.getName()));
        hoofdgerecht_child_list.add(grill);
        categoryRepository.saveAndFlush(grill);



        var pizza = new Category();
        pizza.setName("Pizza");
        pizza.setImage("https://familiefavorieten.nl/wp-content/uploads/2020/05/Pizza-zelf-maken.jpg");
        pizza.setVisible(true);
        pizza.setProducts(getProductsOfCategory(pizza.getName()));
        hoofdgerecht_child_list.add(pizza);
        categoryRepository.saveAndFlush(pizza);



        var ijs = new Category();
        ijs.setName("Ijs");
        ijs.setImage("https://image.15gram.be/uploads/recipes/8945_gemakkelijk-bananenchocolade-ijs_1610795651-1920x1280.jpg");
        ijs.setVisible(true);
        ijs.setProducts(getProductsOfCategory(ijs.getName()));
        nagerecht_child_list.add(ijs);
        categoryRepository.saveAndFlush(ijs);

        var snoepgoed = new Category();
        snoepgoed.setName("Snoepgoed");
        snoepgoed.setImage("https://www.foodlog.nl/images/made/2a703e01277aca84/snoepgoed_1024_617_84_c1.jpg");
        snoepgoed.setVisible(true);
        snoepgoed.setProducts(getProductsOfCategory(snoepgoed.getName()));
        nagerecht_child_list.add(snoepgoed);
        categoryRepository.saveAndFlush(snoepgoed);



        var dranken = new Category();
        dranken.setName("Dranken");
        dranken.setImage("https://www.horecagoedkoop.nl/media/mageplaza/blog/post/d/e/de_5_meest_gedronken_frisdranken.jpg");
        dranken.setVisible(true);
        dranken.setProducts(getProductsOfCategory(dranken.getName()));
        categoryRepository.saveAndFlush(dranken);


        var voorgerecht = new Category();
        voorgerecht.setName("Voorgerecht");
        voorgerecht.setImage("https://chickslovefood.com/wp-content/uploads/2018/12/voorgerecht-coquille-eindfoto2-chickslovefood-1000x640.jpg");
        voorgerecht.setVisible(true);
        voorgerecht.setChildCategories(voorgerecht_child_list);
        categoryRepository.saveAndFlush(voorgerecht);

        var hoofdgerecht = new Category();
        hoofdgerecht.setName("Hoofdgerecht");
        hoofdgerecht.setImage("https://www.dehoutenklaas.nl/wp-content/uploads/2016/12/hoofdgerecht.jpg");
        hoofdgerecht.setVisible(true);
        hoofdgerecht.setChildCategories(hoofdgerecht_child_list);
        categoryRepository.saveAndFlush(hoofdgerecht);

        var nagerecht = new Category();
        nagerecht.setName("Nagerecht");
        nagerecht.setImage("https://smulweb-infra-smulwebbackendpublicbucket7daa1a81-1um3gn7obc0l4.s3.eu-central-1.amazonaws.com/public/sites/default/files/recipe-images/Halloweentoetjes.jpg?VCMOQYMynpbVt0LJiSzQOLqqSojZnMdA");
        nagerecht.setVisible(true);
        nagerecht.setChildCategories(nagerecht_child_list);
        categoryRepository.saveAndFlush(nagerecht);

        for (var category : categoryRepository.findAll())
        {
            if(category.getProducts().size() > 0)
            {
                MakeRelation_Product_And_Ingredient(category.getProducts());
            }
        }

        var table1 = new RestaurantTable();
        table1.setTableNumber(1);
        table1.setYAxis(0);
        table1.setXAxis(1);
        tableRepository.saveAndFlush(table1);

        var table2 = new RestaurantTable();
        table2.setTableNumber(2);
        table2.setYAxis(0);
        table2.setXAxis(2);
        tableRepository.saveAndFlush(table2);

        var table3 = new RestaurantTable();
        table3.setTableNumber(3);
        table3.setYAxis(1);
        table3.setXAxis(1);
        tableRepository.saveAndFlush(table3);
    }

    private void setTags() {
        var tag1 = new Tag();
        tag1.setName("Gluten");
        tagRepository.saveAndFlush(tag1);

        var tag2 = new Tag();
        tag2.setName("Vegetarisch");
        tagRepository.saveAndFlush(tag2);

        var tag3 = new Tag();
        tag3.setName("Alcohol");
        tagRepository.saveAndFlush(tag3);
    }

    private List<Tag> getRandomTags() {
        List<Tag> tags = tagRepository.findAll();
        List<Tag> returnTags = new ArrayList<>();
        for (Tag tag : tags) {
            int random_int = (int) Math.floor(Math.random() * (100));
            System.out.println(random_int);
            if (random_int > 65)
                returnTags.add(tag);
        }
        return returnTags;
    }

    private void setIngredients() {
        var ingredients = new ArrayList<Ingredient>();
        var yoghurt = new Ingredient();
        yoghurt.setName("yoghurt");
        ingredients.add(yoghurt);

        var komkommer = new Ingredient();
        komkommer.setName("komkommer");
        ingredients.add(komkommer);


        var tomaat = new Ingredient();
        tomaat.setName("tomaat");
        ingredients.add(tomaat);

        var zalm = new Ingredient();
        zalm.setName("zalm");
        ingredients.add(zalm);

        var knoflooksoep = new Ingredient();
        knoflooksoep.setName("knoflook");
        ingredients.add(knoflooksoep);

        var OlijfOlie = new Ingredient();
        OlijfOlie.setName("olijf olie");
        ingredients.add(OlijfOlie);

        var Blauwekaas = new Ingredient();
        Blauwekaas.setName("blauwe schimmelkaas");
        ingredients.add(Blauwekaas);

        var rodeui = new Ingredient();
        rodeui.setName("rode ui");
        ingredients.add(rodeui);

        var spek = new Ingredient();
        spek.setName("spek");
        ingredients.add(spek);


        var deeg = new Ingredient();
        deeg.setName("deeg");
        ingredients.add(deeg);

        var Tomatensauss = new Ingredient();
        Tomatensauss.setName("Tomatensaus");
        ingredients.add(Tomatensauss);

        var mozzeralla = new Ingredient();
        mozzeralla.setName("mozzeralla");
        ingredients.add(mozzeralla);


        var varkensvlees = new Ingredient();
        varkensvlees.setName("Varkens vlees");
        ingredients.add(varkensvlees);


        var smurfenkots = new Ingredient();
        smurfenkots.setName("smurfen kots");
        ingredients.add(smurfenkots);

        var vanilleijs = new Ingredient();
        vanilleijs.setName("vanille ijs");
        ingredients.add(vanilleijs);


        var melk = new Ingredient();
        melk.setName("opgeklopte melk");
        ingredients.add(melk);

        var koffie = new Ingredient();
        koffie.setName("koffie");
        ingredients.add(koffie);


        var chocolade = new Ingredient();
        chocolade.setName("chocolade");
        ingredients.add(chocolade);

        for (Ingredient ingredient : ingredients) {
            ingredientRepository.saveAndFlush(ingredient);
        }
    }

    private List<Product> getProductsOfCategory(String category) {
        List<Product> products = new ArrayList<>();
        switch (category) {
            case "Salade":
                Product tamara = new Product();
                tamara.setName("Tamara");
                tamara.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Taramosalata01.jpg/230px-Taramosalata01.jpg");
                tamara.setPrice(5.00);
                tamara.setDescription("Viskruidsalade");
                tamara.setContainsAlcohol(false);
                tamara.setTags(getRandomTags());
                products.add(tamara);
                break;

            case "Yoghurt":
                Product tzatziki = new Product();
                tzatziki.setName("Tzatziki");
                tzatziki.setImage("https://img.static-rmg.be/a/food/image/q75/w640/h400/1086911/tzatziki.jpg");
                tzatziki.setPrice(4.50);
                tzatziki.setDescription("Griekse yoghurt met knoflook en komkommer");
                tzatziki.setContainsAlcohol(false);
                tzatziki.setTags(getRandomTags());
                products.add(tzatziki);
                break;

            case "Soep":
                Product tomatensoep = new Product();
                tomatensoep.setName("Tomatensoep");
                tomatensoep.setImage("https://www.matchusports.nl/wp-content/uploads/2020/09/Tomatensoep-880-x-495.jpg");
                tomatensoep.setPrice(4.75);
                tomatensoep.setDescription("lekker man");
                tomatensoep.setContainsAlcohol(false);
                tomatensoep.setTags(getRandomTags());
                products.add(tomatensoep);
                break;

            case "Grill":
                Product gyros = new Product();
                gyros.setName("Gyros");
                gyros.setImage("https://www.24kitchen.nl/files/styles/social_media_share/public/2021-05/gyros-hor.jpg?itok=2QDb5Hkd");
                gyros.setPrice(15.50);
                gyros.setDescription("Gesneden varkensvlees met tzatziki, mayonaise, frites en koolsalade");
                gyros.setContainsAlcohol(false);
                gyros.setTags(getRandomTags());
                products.add(gyros);
                break;
            case "Pizza":
                Product margherita = new Product();
                margherita.setName("Margherita");
                margherita.setImage("https://pizzametpazzi.nl/wp-content/uploads/2017/02/pizzamargherita_orig-600x400.jpeg");
                margherita.setPrice(5.00);
                margherita.setDescription("Pizza met tomaat en kaas");
                margherita.setContainsAlcohol(false);
                margherita.setTags(getRandomTags());
                products.add(margherita);

                Product gorgonzola = new Product();
                gorgonzola.setName("Gorgonzola");
                gorgonzola.setImage("https://lekkeritaliaanskoken.nl/media/k2/items/cache/0c758c944f0062609b2677e28107bc5e_M.jpg");
                gorgonzola.setPrice(8.50);
                gorgonzola.setDescription("Pizza met tomaten, kaas en gorgonzola");
                gorgonzola.setContainsAlcohol(false);
                gorgonzola.setTags(getRandomTags());
                products.add(gorgonzola);
                break;

            case "Ijs":
                Product smurfijs = new Product();
                smurfijs.setName("Smurf ijs");
                smurfijs.setImage("https://www.funfoodshop.nl/image/smurfijs_1.jpg?w=1200&h=1000&r=1");
                smurfijs.setPrice(2.00);
                smurfijs.setDescription("Blauw ijs en das mooi");
                smurfijs.setContainsAlcohol(false);
                smurfijs.setTags(getRandomTags());
                products.add(smurfijs);

                Product VanilleIjs = new Product();
                VanilleIjs.setName("Vanille ijs");
                VanilleIjs.setImage("https://www.funfoodshop.nl/image/smurfijs_1.jpg?w=1200&h=1000&r=1");
                VanilleIjs.setPrice(2.00);
                VanilleIjs.setDescription("Voor de saaie mensen");
                VanilleIjs.setContainsAlcohol(false);
                VanilleIjs.setTags(getRandomTags());
                products.add(VanilleIjs);
                break;
            case "Dranken":
                Product cappuccino = new Product();
                cappuccino.setName("Cappuccino");
                cappuccino.setImage("https://www.jacobsdouweegbertsprofessional.nl/globalassets/cappuccino-maken/cappuccino-maken6v1.jpg?preset=two-halves-image-mobile");
                cappuccino.setPrice(2.75);
                cappuccino.setDescription("Koffie met créme laagje witte wel");
                cappuccino.setContainsAlcohol(false);
                cappuccino.setTags(getRandomTags());
                products.add(cappuccino);
                break;
            case "Snoepgoed":
                Product kwatta = new Product();
                kwatta.setName("Kwatta");
                kwatta.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Chocolate02.png/520px-Chocolate02.png");
                kwatta.setPrice(3.00);
                kwatta.setDescription("Heerlijke chocolade blokjes");
                kwatta.setContainsAlcohol(false);
                kwatta.setTags(getRandomTags());
                products.add(kwatta);
                break;
        }
        for (Product product : products) {
            productRepository.saveAndFlush(product);
        }
        return products;
    }

    private List<Ingredient> getIngredientsForProduct(String name) {
        List<Ingredient> ingredients = new ArrayList<>();
        switch (name) {
            case "Tzatziki":
                ingredients.add(ingredientRepository.getIngredientByName("komkommer"));
                ingredients.add(ingredientRepository.getIngredientByName("yoghurt"));
                break;
            case "Tamara":
                ingredients.add(ingredientRepository.getIngredientByName("tomaat"));
                ingredients.add(ingredientRepository.getIngredientByName("zalm"));
                break;
            case "Tomatensoep":
                ingredients.add(ingredientRepository.getIngredientByName("knoflook"));
                ingredients.add(ingredientRepository.getIngredientByName("tomaat"));
                ingredients.add(ingredientRepository.getIngredientByName("olijf olie"));
                break;
            case "Gorgonzola":
                ingredients.add(ingredientRepository.getIngredientByName("blauwe schimmelkaas"));
                ingredients.add(ingredientRepository.getIngredientByName("Tomatensaus"));
                ingredients.add(ingredientRepository.getIngredientByName("rode ui"));
                break;
            case "Margherita":
                ingredients.add(ingredientRepository.getIngredientByName("deeg"));
                ingredients.add(ingredientRepository.getIngredientByName("Tomatensaus"));
                ingredients.add(ingredientRepository.getIngredientByName("mozzeralla"));
                break;
            case "Gyros":
                ingredients.add(ingredientRepository.getIngredientByName("Varkens vlees"));
                break;
            case "Smurfijs":
                ingredients.add(ingredientRepository.getIngredientByName("smurfen kots"));
                ingredients.add(ingredientRepository.getIngredientByName("vanille ijs"));
                break;
            case "Cappucino":
                ingredients.add(ingredientRepository.getIngredientByName("opgeklopte melk"));
                ingredients.add(ingredientRepository.getIngredientByName("koffie"));
                break;
            case "Kwatta":
                ingredients.add(ingredientRepository.getIngredientByName("chocolade"));
                break;

        }
        return ingredients;
    }

    private void MakeRelation_Product_And_Ingredient(List<Product> products) {
        for (Product product : products) {
            List<Ingredient> ingredients = getIngredientsForProduct(product.getName());
            if (ingredients.size() > 0) {
                for (Ingredient ingredient : ingredients) {
                    IngredientProduct ingredientProduct = new IngredientProduct();
                    ingredientProduct.setIngredient(ingredient);
                    ingredientProduct.setProduct(product);
                    ingredientProduct.setRequired(true);
                    ingredientProductRepository.saveAndFlush(ingredientProduct);
                }
            }
        }
    }

}