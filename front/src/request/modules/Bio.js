import axios from '../axios.js'

/**
 * biochemestry
 */
const bioDataUrl = window.global.baseUrl +"/rest/dataBiochemistry";
const bioStationUrl = window.global.baseUrl +"/rest/rcBasicCruiseStanceInfo";

export const queryByCruiseSeg = (id) => {
    return axios.post({
        url: bioStationUrl + '/query/cruiseSeg',
        method: 'post',
        data:axios.newFD({
            segId:id
        })
    })
};

export const datasByStationSeg = (stationName,segId) => {
    return axios.post({
        url:  bioDataUrl + '/datas/byStationSeg',
        method: 'post',
        data:axios.newFD({
            stationName,
            segId
        })
    })
};