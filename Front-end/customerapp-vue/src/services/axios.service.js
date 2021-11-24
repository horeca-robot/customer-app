import axios from 'axios';
import pathEnum from '../enum/index'

const API_URL = 'http://localhost:8080/';

class APIService{
    //Table
    getRestaurantTables(){
        return axios.get(API_URL + pathEnum.TABLES);
    }
    getRestaurantTableNumberById(id){
        return axios.get(API_URL + pathEnum.TABLE_NUMBER_BY_ID + id);
    }
}

export default new APIService();