const pathEnum = Object.freeze({
    ORDER: "/api/v1/order/",
    ORDERS: "/api/v1/order/bytable?restaurantTableId=",
    ORDER_BY_ID: "/api/v1/order/byid",
    CATEGORY_BY_ID: "/api/v1/category/byid?id=",
    CATEGORIES: "/api/v1/category/all",
    PRODUCT_BY_ID: "/api/v1/product/byid",
    INGREDIENTS_BY_PRODUCTID: "/api/v1/ingredient/byproductid",
    PRODUCTS: "/api/v1/product/all",
    TABLES: '/api/v1/table/all',
    TABLE_NUMBER_BY_ID: '/api/vi/table/byid?id=',
    RESTAURANT_INFO: '/api/v1/info/'
});

export default pathEnum;