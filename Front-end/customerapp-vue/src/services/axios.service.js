import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1/';

class AxiosService{
    //Table
    getRestaurantTables(){
        return axios.get(API_URL + 'table/all');
    }
}

export default new AxiosService();