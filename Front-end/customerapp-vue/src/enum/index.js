const pathEnum = Object.freeze({
    ORDER: "/api/v1/order/",
    DOWNLOAD_BILL: "/api/v1/order/download?restaurantTableId=",
    ORDERS: "/api/v1/order/bytable?restaurantTableId=",
    ORDER_BY_ID: "/api/v1/order/byid",
    CATEGORY_BY_ID: "/api/v1/category/byid?id=",
    CATEGORIES: "/api/v1/category/all",
    PARENTCATEGORIES: "/api/v1/category/all/parent",
    PRODUCT_BY_ID: "/api/v1/product/byid",
    INGREDIENTS_BY_PRODUCTID: "/api/v1/ingredient/byproductid",
    PRODUCTS: "/api/v1/product/all",
    TABLES: '/api/v1/table/all',
    TABLE_NUMBER_BY_ID: '/api/v1/table/byid?id=',
    RESTAURANT_INFO: '/api/v1/info/',
    TAGS: '/api/v1/tag/all',
});

export default pathEnum;