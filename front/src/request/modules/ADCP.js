/**
 * adcp
 */
import axios from '../axios.js'

const adcpDataUrl = window.global.baseUrl +"/rest/dataAdcp";
const adcpStationUrl = window.global.baseUrl +"/rest/stationAdcp";

export const queryByCruiseSeg = (id) => {
    return axios.post({
        url: adcpStationUrl + '/query/cruiseSeg',
        method: 'post',
        data:axios.newFD({
            segId:id
        })
    })
};


export const datasByStationRelateId = (id) => {
    return axios.post({
        url:  adcpDataUrl + '/datas/relateId',
        method: 'post',
        data:axios.newFD({
            relateId:id
        })
    })
};