import axios from '../axios.js'

const cruiseUrl = window.global.baseUrl +"/rest/rcBasicCruiseInfo";
const cruiseProUrl = window.global.baseUrl +"/rest/rcBasicCruiseProjectInfo";

export const queryByCruiseId = (id) => {
    return axios.post({
        url: cruiseUrl + '/query',
        method: 'post',
        data:axios.newFD({
            cruiseId:id
        })
    })
};

export const queryLike = (year,name) => {
    return axios.post({
        url:  cruiseProUrl + '/queryLike',
        method: 'post',
        data:axios.newFD({
            year,
            name
        })
    })
};