/***
 * CTD
 */
import axios from '../axios.js'

const ctdDataUrl = window.global.baseUrl +"/rest/dataCtd";
const ctdStationUrl = window.global.baseUrl +"/rest/rcBasicCruiseStanceInfo";

export const queryByCruiseSeg = (id) => {
    return axios.post({
        url: ctdStationUrl + '/query/cruiseSeg',
        method: 'post',
        data:axios.newFD({
            segId:id
        })
    })
};

export const datasByStationSeg = (stationName,segId) => {
    return axios.post({
        url:  ctdDataUrl + '/datas/byStationSeg',
        method: 'post',
        data:axios.newFD({
            stationName,
            segId
        })
    })
};