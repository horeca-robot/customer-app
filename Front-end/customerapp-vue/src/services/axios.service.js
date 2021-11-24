import axios from 'axios';
import pathEnum from '../enum/index'

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
        return this.get(pathEnum.PRODUCT_BY_ID, params)
    }

    getByProductId(params) {
        return this.get(pathEnum.BYPRODUCT_ID, params)
    }

    placeOrder(order) {
        return this.post(pathEnum.ORDER, order)
    }

    get(path) {
        return axios.get(path);
    }

    get(path, params) {
        return axios.get(path, params);
    }

    post(path, data) {
        return axios.post(path, data)
    }
}

export default new APIService();