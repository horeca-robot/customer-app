import axios from "axios";
import pathEnum from "../enum/index"

const baseURL = "http://localhost:8080";
axios.defaults.baseURL = baseURL;

class APIService{
    getRestaurantTables(){
        return this.get(pathEnum.TABLES);
    }

    getRestaurantTableNumberById(id){
        return this.get(pathEnum.TABLE_NUMBER_BY_ID + id);
    }
    
    getRestaurantInfo(){
        return this.get(pathEnum.RESTAURANT_INFO);
    }

    getCategoryById(id) {
        return this.get(pathEnum.CATEGORY_BY_ID + id)
    }

    getAllCategories() {
        return this.get(pathEnum.CATEGORIES)
    }

    getAllParentCategories() {
        return this.get(pathEnum.PARENTCATEGORIES)
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

    getAllTags() {
        return this.get(pathEnum.TAGS);
    }

   getDownloadBill(id) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', baseURL + pathEnum.DOWNLOAD_BILL + id, true);
        xhr.responseType = 'arraybuffer';
        xhr.onload = function() {
        if (this.status == 200) {
          var blob=new Blob([this.response], {type:"application/pdf"});
          var link=document.createElement('a');
          link.href=window.URL.createObjectURL(blob);
          link.download="Rekening"+ new Date(Date.now()) +".pdf";
          link.click();
        }
        xhr.send();
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
