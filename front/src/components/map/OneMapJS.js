import * as CTD from "@/request/modules/CTD"
import * as ADCP from "@/request/modules/ADCP"
import * as Bio from "@/request/modules/Bio"

import {ToDegrees} from "./lib/gis"

let popData = async  function(type, prop) {
    function fp(key) { // format prop's key
        return prop[key] ? prop[key] : "-"
    }

    function fN(key, retain = 2) { // format prop's key number
        return prop[key] ? prop[key].toFixed(retain) : "-"
    }
    function fNS(key){
        return ToDegrees(prop[key] + "")
    }
    let pop = {
        type: "",
        status: false,
        title: '',
        pic: '',
        prop: {},
        spans: [],
        labels: [],
        btns: []
    };
    pop.type = type;
    pop.prop = prop;
    switch (type) {
        case "":
            pop.title = '基础站位';
            pop.spans = [{
                name: "站名",
                value: fp('cruiseStanceName'),
                unit: " "
            }, {
                name: "水深",
                value: fN('waterDept'),
                unit: "米"
            }];
            pop.labels = [{
                name: "坐标",
                value: fNS('stanceLong') + " E  " + fNS('stanceLat') +  "N"
            },{
                name: "到站时间",
                value: fp('arrStationTime')
            }, {
                name: "离站时间",
                value: fp('outStationTime')
            },{
                name: "备注",
                value: fp('remark')
            }];
            break;
        case "CTD":
            await CTD.datasByStationSeg(prop['cruiseStanceName'], prop['cruiseSegmentId']).then((datas) => {
                if (Array.isArray(datas)) {
                    let first = datas[0];
                    delete first['remark']
                    Object.assign(prop, first);
                    prop.list = datas
                    pop.title = 'CTD站位 ' + fp('cruiseStanceName');
                    pop.spans = [{
                        name: "表层深度",
                        value: fN('obsDepth'),
                        unit: "米"
                    },{
                        name: "观测标识",
                        value: fp('obsFlag'),
                        unit: ""
                    }, {
                        name: "压力",
                        value: fN('pressure'),
                        unit: " hPa"
                    }, {
                        name: "水温",
                        value: fN('temperature'),
                        unit: " °C"
                    }, {
                        name: "电导率",
                        value: fN('conductivity'),
                        unit: " S/m"
                    }, {
                        name: "盐度",
                        value: fN('salinity'),
                        unit: " "
                    }, {
                        name: "浊度",
                        value: fN('turbidity'),
                        unit: " FTU"
                    }, {
                        name: "声速",
                        value: fN('soundVelocity'),
                        unit: "m/s"
                    }];
                    pop.labels = [{
                        name: "坐标",
                        value: fNS('stanceLong') + " E  " + fNS('stanceLat') + " N"
                    },{
                        name: "到站时间",
                        value: fp('arrStationTime')
                    }, {
                        name: "离站时间",
                        value: fp('outStationTime')
                    }];
                    pop.btns = [{
                        type: "CTD可视化产品",
                        name: "CTD可视化产品"
                    }];
                }
            })
            break;
        case "生物化学":
            await Bio.datasByStationSeg(prop['cruiseStanceName'], prop['cruiseSegmentId']).then((datas) => {
                if (Array.isArray(datas)) {
                    let first = datas[0];
                    delete first['remark']
                    Object.assign(prop, first);
                    prop.list = datas
                    pop.title = '生物化学站位 ' + fp('cruiseStanceName');
                    pop.spans = [{
                        name: "表层深度",
                        value: fN('obsDepth'),
                        unit: "米"
                    }, {
                        name: "水温",
                        value: fN('temperature'),
                        unit: " ℃"
                    }, {
                        name: "盐度",
                        value: fN('salinity'),
                        unit: ""
                    }, {
                        name: "溶解氧",
                        value: fN('dissolvedOxygen'),
                        unit: "µmol/kg"
                    }, {
                        name: "ph",
                        value: fN('ph'),
                        unit: ""
                    }, {
                        name: "氨氮",
                        value: fN('nh4N'),
                        unit: "µmol/kg"
                    }, {
                        name: "硝酸盐",
                        value: fN('no3N'),
                        unit: "µmol/kg"
                    }, {
                        name: "硅酸盐",
                        value: fN('sio4'),
                        unit: "µmol/kg"
                    }, {
                        name: "亚硝酸盐",
                        value: fN('no2N'),
                        unit: "µmol/kg"
                    }, {
                        name: "活性磷酸盐",
                        value: fN('po4'),
                        unit: "µmol/kg"
                    }, {
                        name: "叶绿素a",
                        value: fN('chlorophylA'),
                        unit: "mg/m3"
                    }];
                    pop.labels = [{
                        name: "到站时间",
                        value: fp('arrStationTime')
                    }, {
                        name: "离站时间",
                        value: fp('outStationTime')
                    }, {
                        name: "坐标",
                        value: fNS('stanceLong') + " E  " + fNS('stanceLat') + " N"
                    }];
                    pop.btns = [{
                        type: "生物化学可视化产品",
                        name: "生物化学可视化产品"
                    }];
                }
            })
            break;
        case "ADCP":
            await ADCP.datasByStationRelateId(prop['relateId']).then((datas) => {
                if (Array.isArray(datas)) {
                    let first = datas[0];
                    delete first['remark']
                    Object.assign(prop, first);
                    prop.list = datas
                }
                pop.title = 'ADCP站位';
                pop.spans = [{
                    name: "表层深度",
                    value: fN('obsDepth'),
                    unit: "米"
                }, {
                    name: "水平流速",
                    value: fN('horizontalVelocity'),
                    unit: "cm/s"
                }, {
                    name: "水平流向",
                    value: fN('horizontalDirection'),
                    unit: " °"
                }, {
                    name: "垂直流速",
                    value: fN('verticalVelocity'),
                    unit: "cm/s"
                }];
                pop.labels = [{
                    name: "观测时间",
                    value: fp('date')
                }, {
                    name: "坐标",
                    value: fNS('stationLon') + " E  " + fNS('stationLat') + " N"
                }, {
                    name: "备注",
                    value: fp('remark')
                }];
                pop.btns = [{
                    type: "ADCP可视化产品",
                    name: "ADCP可视化产品"
                }];
            })
            break;
        case "海洋气象":
            pop.title = '海洋气象站位';
            pop.spans = [{
                name: "观测高度",
                value: fN('obsHeight'),
                unit: " 米"
            }, {
                name: "气压",
                value: fN('airPressure'),
                unit: " hPa"
            }, {
                name: "气温",
                value: fN('airTemperature'),
                unit: " ℃"
            }, {
                name: "风速",
                value: fN('windVelocity'),
                unit: " m/s"
            }, {
                name: "风向",
                value: fN('windDirection'),
                unit: " °"
            }, {
                name: "相对湿度",
                value: fN('humidity'),
                unit: " %"
            }, {
                name: "降水",
                value: fN('rainfall'),
                unit: " mm"
            }];
            pop.labels = [{
                name: "观测时间",
                value: fp('date')
            }, {
                name: "坐标",
                value: fNS('stationLon') + " E " + fNS('stationLat') + " N"
            }, {
                name: "备注",
                value: fp('remark')
            }];
            break;
        default:
            break
    }
    return pop;
};

export { popData }