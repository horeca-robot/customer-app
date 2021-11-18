import axios from 'axios';
import pathEnum from '../enum/index'

const API_URL = 'http://localhost:8080/';

class AxiosRouterService{
    //Table
    getRestaurantTables(){
        return axios.get(API_URL + pathEnum.TABLES);
    }
}

export default new AxiosRouterService();