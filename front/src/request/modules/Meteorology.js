/**
 * 海洋气象
 */
import axios from '../axios.js'

const baseUrl = window.global.baseUrl +"/rest/stationMeteorology";
export const baseQuery = (data) => {
    return axios.post({
        url: baseUrl + '/query',
        method: 'post',
        data
    })
};

export const spaceQuery = (extent,thin,segId) => {
    return axios.post({
        url:  baseUrl + '/spaceQuery',
        method: 'post',
        data:axios.newFD({
            extent,
            thin,
            segId
        })
    })
};