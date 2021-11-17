import axios from "axios";
axios.defaults.baseURL = 'http://localhost:8080'

class AxiosService {
    post(path, data) {
        axios.post(path, data)
        .then(response => {
            return response.data
        })
        .catch(err => {
            console.log(err, err.response)
            return err.response
        })
    }

    get(path) {
        axios.get(path)
        .then(response => {
            return response.data
        })
        .catch(err => {
            console.log(err, err.response)
            return err.response
        })
    }

    get(path, params) {
        axios.get(path, params)
        .then(response => {
            return response.data
        })
        .catch(err => {
            console.log(err, err.response)
            return err.response
        })
    }
}

export default new AxiosService();