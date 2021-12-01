import axios from "axios";
import pathEnum from "../enum/index"

axios.defaults.baseURL = "http://localhost:8080"

class APIService{
    getRestaurantTables(){
        return this.get(pathEnum.TABLES);
    }

    getRestaurantTableNumberById(id){
        return this.get(pathEnum.TABLE_NUMBER_BY_ID + id);
    }

    getCategoryById(id) {
        return this.get(pathEnum.CATEGORY_BY_ID + id)
    }

    getAllCategories() {
        return this.get(pathEnum.CATEGORIES)
    }

    getProductById(params) {
        return this.getWithParams(pathEnum.PRODUCT_BY_ID, params)
    }

    getIngredientsByProductId(params) {
        return this.getWithParams(pathEnum.INGREDIENTS_BY_PRODUCTID, params)
    }

    getAllProducts(){
        return this.get(pathEnum.PRODUCTS)
    }

    placeOrder(order) {
        return this.post(pathEnum.ORDER, order)
    }

    getOrdersById(id) {
        return this.get(pathEnum.ORDERS + id)
    }

    getOrderDetails(params) {
        return this.getWithParams(pathEnum.ORDER_BY_ID, params);
    }

    getDownloadBill(id) {
        return this.get(pathEnum.DOWNLOAD_BILL + id)
    }

    get(path) {
        return axios.get(path);
    }

    getWithParams(path, params) {
        return axios.get(path, params);
    }

    post(path, data) {
        return axios.post(path, data)
    }
}

export default new APIService();
