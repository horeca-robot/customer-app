package com.customerapp.CustomerAppApi.core;

import com.customerapp.CustomerAppApi.models.ProductDto;
import com.customerapp.CustomerAppApi.models.ProductOrderDto;
import edu.fontys.horecarobot.databaselibrary.enums.OrderStatus;
import edu.fontys.horecarobot.databaselibrary.models.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MockFactory {

    //Products
    public List<Product> getMockProducts() {
        List<Product> products = new ArrayList<>();

        Product cola = new Product();
        cola.setId(UUID.randomUUID());
        cola.setName("Cola");
        cola.setPrice(2.50);
        cola.setContainsAlcohol(false);
        products.add(cola);

        Product fanta = new Product();
        fanta.setId(UUID.randomUUID());
        fanta.setName("Fanta");
        fanta.setPrice(2.50);
        fanta.setContainsAlcohol(false);
        products.add(fanta);

        Product sprite = new Product();
        sprite.setId(UUID.randomUUID());
        sprite.setName("Sprite");
        sprite.setPrice(2.50);
        sprite.setContainsAlcohol(false);
        products.add(sprite);

        return products;
    }

    public List<ProductDto> getProductDtoList()
    {
        List<ProductDto> productDtoList = new ArrayList<>();
        for(var product : getMockProducts())
        {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setContainsAlcohol(product.isContainsAlcohol());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    //IngredientProducts
    public List<IngredientProduct> getmockIngredientProducts(){
        List<IngredientProduct> ingredientProducts = new ArrayList<>();

        IngredientProduct ingredientProduct1 = new IngredientProduct();
        ingredientProduct1.setIngredient(getmockIngredients().get(0));
        ingredientProduct1.setProduct(getMockProducts().get(0));
        ingredientProduct1.setId(UUID.randomUUID());
        ingredientProduct1.setRequired(false);

        IngredientProduct ingredientProduct2 = new IngredientProduct();
        ingredientProduct2.setIngredient(getmockIngredients().get(1));
        ingredientProduct2.setProduct(getMockProducts().get(1));
        ingredientProduct2.setId(UUID.randomUUID());
        ingredientProduct2.setRequired(false);

        IngredientProduct ingredientProduct3 = new IngredientProduct();
        ingredientProduct3.setIngredient(getmockIngredients().get(2));
        ingredientProduct3.setProduct(getMockProducts().get(2));
        ingredientProduct3.setId(UUID.randomUUID());
        ingredientProduct3.setRequired(false);

        ingredientProducts.add(ingredientProduct1);
        ingredientProducts.add(ingredientProduct2);
        ingredientProducts.add(ingredientProduct3);

        return ingredientProducts;

    }

    //Ingredient
    public List<Ingredient> getmockIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Kaas");
        ingredient1.setId(UUID.randomUUID());
        ingredient1.setDescription("test");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Melk");
        ingredient2.setId(UUID.randomUUID());
        ingredient2.setDescription("test");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setName("Yoghurt");
        ingredient3.setId(UUID.randomUUID());
        ingredient3.setDescription("test");

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        return ingredients;
    }

    //Tables
    public RestaurantTable getMockRestaurantTable()
    {
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableNumber(1);
        restaurantTable.setId(UUID.randomUUID());
        restaurantTable.setXAxis(0);
        restaurantTable.setYAxis(0);
        return restaurantTable;
    }

    public List<RestaurantTable> getMockRestaurantTableList()
    {
        List<RestaurantTable> restaurantTableList = new ArrayList<>();
        RestaurantTable restaurantTable1 = new RestaurantTable();
        restaurantTable1.setTableNumber(1);
        restaurantTable1.setId(UUID.randomUUID());
        restaurantTable1.setXAxis(0);
        restaurantTable1.setYAxis(0);

        RestaurantTable restaurantTable2 = new RestaurantTable();
        restaurantTable2.setTableNumber(1);
        restaurantTable2.setId(UUID.randomUUID());
        restaurantTable2.setXAxis(0);
        restaurantTable2.setYAxis(0);

        RestaurantTable restaurantTable3 = new RestaurantTable();
        restaurantTable3.setTableNumber(1);
        restaurantTable3.setId(UUID.randomUUID());
        restaurantTable3.setXAxis(0);
        restaurantTable3.setYAxis(0);

        restaurantTableList.add(restaurantTable1);
        restaurantTableList.add(restaurantTable2);
        restaurantTableList.add(restaurantTable3);

        return restaurantTableList;
    }

    //RestaurantOrders
    public List<RestaurantOrder> getmockRestaurantOrders()
    {
        List<RestaurantOrder> restaurantOrders = new ArrayList<>();

        RestaurantOrder restaurantOrder1 = new RestaurantOrder();
        restaurantOrder1.setId(UUID.randomUUID());
        restaurantOrder1.setTable(getMockRestaurantTable());
        restaurantOrder1.setProductOrders(getmockProductOrders());
        restaurantOrder1.setSubTotal(0);
        restaurantOrder1.setCreatedAt(new Date());
        restaurantOrder1.setPaid(false);
        restaurantOrder1.setNote("Test");

        RestaurantOrder restaurantOrder2 = new RestaurantOrder();
        restaurantOrder2.setId(UUID.randomUUID());
        restaurantOrder2.setTable(getMockRestaurantTable());
        restaurantOrder2.setProductOrders(getmockProductOrders());
        restaurantOrder2.setSubTotal(0);
        restaurantOrder2.setCreatedAt(new Date());
        restaurantOrder2.setPaid(false);
        restaurantOrder2.setNote("Test");

        RestaurantOrder restaurantOrder3 = new RestaurantOrder();
        restaurantOrder3.setId(UUID.randomUUID());
        restaurantOrder3.setTable(getMockRestaurantTable());
        restaurantOrder3.setProductOrders(getmockProductOrders());
        restaurantOrder3.setSubTotal(0);
        restaurantOrder3.setCreatedAt(new Date());
        restaurantOrder3.setPaid(false);
        restaurantOrder3.setNote("Test");

        restaurantOrders.add(restaurantOrder1);
        restaurantOrders.add(restaurantOrder2);
        restaurantOrders.add(restaurantOrder3);

        return restaurantOrders;
    }

    //ProductOrders
    public List<ProductOrder> getmockProductOrders(){
        List<ProductOrder> productOrders = new ArrayList<>();

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProduct(getMockProducts().get(0));
        productOrder1.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);
        productOrder1.setId(UUID.randomUUID());

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setProduct(getMockProducts().get(1));
        productOrder2.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);
        productOrder2.setId(UUID.randomUUID());

        ProductOrder productOrder3 = new ProductOrder();
        productOrder3.setProduct(getMockProducts().get(2));
        productOrder3.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);
        productOrder3.setId(UUID.randomUUID());

        productOrders.add(productOrder1);
        productOrders.add(productOrder2);
        productOrders.add(productOrder3);

        return productOrders;
    }

    public List<ProductOrderDto> getmockProductOrderDtoList()
    {
        List<ProductOrderDto> productOrderDtoList = new ArrayList<>();

        ProductOrderDto productOrderDto1 = new ProductOrderDto();
        productOrderDto1.setId(UUID.randomUUID());
        productOrderDto1.setProduct(getProductDtoList().get(0)); //Cola
        productOrderDto1.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);

        ProductOrderDto productOrderDto2 = new ProductOrderDto();
        productOrderDto2.setId(UUID.randomUUID());
        productOrderDto2.setProduct(getProductDtoList().get(1)); //Fanta
        productOrderDto2.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);

        ProductOrderDto productOrderDto3 = new ProductOrderDto();
        productOrderDto3.setId(UUID.randomUUID());
        productOrderDto3.setProduct(getProductDtoList().get(2)); //Sprite
        productOrderDto3.setOrderStatus(OrderStatus.OPEN_FOR_DELIVERY);

        productOrderDtoList.add(productOrderDto1);
        productOrderDtoList.add(productOrderDto2);
        productOrderDtoList.add(productOrderDto3);

        return productOrderDtoList;
    }

    //Categories
    public List<Category> getmockCategories(){
        List<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setProducts(getMockProducts());
        category.setName("Dranken");
        category.setId(UUID.randomUUID());

        categories.add(category);

        return categories;
    }

    //RestaurantInfo
    public RestaurantInfo getmockRestaurantInfo()
    {
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
        return info;
    }
}
