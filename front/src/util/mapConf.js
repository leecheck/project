

import * as maptalks from 'maptalks';

const value = {
    testGround: {
        name: "国家浅海海上综合试验场",
        lonlats: [
            [122.06444, 37.5925],
            [122.064444, 37.5741666],
            [122.0930555, 37.5741666],
            [122.0930555, 37.5925],
            [122.06444, 37.592501]
        ]
    },
    equipCenter: {
        name: "海洋智能装备研究中心",
        lonlat: [122.090555, 37.5419444]
    },
    chudao: {
        name: '褚岛基地',
        lonlat: [122.0836111, 37.4075]
    },
    baseStation: {
        name: 'AIS基站',
        lonlat: [122.135, 37.566]
    },
    server: {
        name: '大数据中心',
        lonlat: [122.117, 37.492]
    },

    testPoints: [{
        name: "jiancedian1",
        lon: 122.8,
        lat: 37
    },{
        name: "jiancedian2",
        lon: 122.7,
        lat: 37
    }],

    ship:[{
        name: "实验船1",
        lon: 122.06544,
        lat: 37.5925,
        angle:122,
        type:"experiment"
    },{
        name: "实验船2",
        lon: 122.5,
        lat: 37.5,
        angle:122,
        type:"experiment"
    },{
        name: "xujsl",
        lon: 122.88,
        lat: 37.23,
        angle:152,
        type:"safe"
    },{
        name: "dhsll",
        lon: 122.72,
        lat: 37.122,
        angle:122,
        type:"near"
    },{
        name: "dhsl33l",
        lon: 122.0830555,
        lat: 37.5925,
        angle:192,
        type:"enter"
    }]
}

value.ods = [{
        coordinates: [
            value.equipCenter.lonlat,
            value.server.lonlat
        ]
    },{
        coordinates: [
            value.baseStation.lonlat,
            value.server.lonlat
        ]
    },{
        coordinates: [
            value.chudao.lonlat,
            value.server.lonlat
        ]
    },{
        coordinates: [
            value.equipCenter.lonlat,
            value.server.lonlat
        ]
    }]
let trendNodes = window.globle.trendNodes;
let DistanceToolConf = {
    'symbol': {
        'lineColor': '#34495e',
        'lineWidth': 2
    },
    'vertexSymbol': {
        'markerType': 'ellipse',
        'markerFill': '#1bbc9b',
        'markerLineColor': '#000',
        'markerLineWidth': 3,
        'markerWidth': 10,
        'markerHeight': 10
    },
    'labelOptions': {
        'textSymbol': {
            'textFaceName': 'monospace',
            'textFill': '#fff',
            'textLineSpacing': 1,
            'textHorizontalAlignment': 'right',
            'textDx': 15,
            'markerLineColor': '#b4b3b3',
            'markerFill': '#000'
        },
        'boxStyle': {
            'padding': [6, 2],
            'symbol': {
                'markerType': 'square',
                'markerFill': '#000',
                'markerFillOpacity': 0.9,
                'markerLineColor': '#b4b3b3'
            }
        }
    },
    'clearButtonSymbol': [{
        'markerType': 'square',
        'markerFill': '#000',
        'markerLineColor': '#b4b3b3',
        'markerLineWidth': 2,
        'markerWidth': 15,
        'markerHeight': 15,
        'markerDx': 20
    }, {
        'markerType': 'x',
        'markerWidth': 10,
        'markerHeight': 10,
        'markerLineColor': '#fff',
        'markerDx': 20
    }],
    'language': 'zh-CN'
};
let AreaToolConf = {
    'symbol': {
        'lineColor': '#1bbc9b',
        'lineWidth': 2,
        'polygonFill': '#fff',
        'polygonOpacity': 0.3
    },
    'vertexSymbol': {
        'markerType': 'ellipse',
        'markerFill': '#34495e',
        'markerLineColor': '#1bbc9b',
        'markerLineWidth': 3,
        'markerWidth': 10,
        'markerHeight': 10
    },
    'labelOptions': {
        'textSymbol': {
            'textFaceName': 'monospace',
            'textFill': '#fff',
            'textLineSpacing': 1,
            'textHorizontalAlignment': 'right',
            'textDx': 15
        },
        'boxStyle': {
            'padding': [6, 2],
            'symbol': {
                'markerType': 'square',
                'markerFill': '#000',
                'markerFillOpacity': 0.9,
                'markerLineColor': '#b4b3b3'
            }
        }
    },
    'clearButtonSymbol': [{
        'markerType': 'square',
        'markerFill': '#000',
        'markerLineColor': '#b4b3b3',
        'markerLineWidth': 2,
        'markerWidth': 15,
        'markerHeight': 15,
        'markerDx': 22
    }, {
        'markerType': 'x',
        'markerWidth': 10,
        'markerHeight': 10,
        'markerLineColor': '#fff',
        'markerDx': 22
    }],
    language: 'zh-CN',
};
let Tile = {
    layer_GG: "http://www.google.cn/maps/vt?lyrs=s@189&gl=cn&x={x}&y={y}&z={z}",
    layerGeoQ: "http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}",
    layerDark: "https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png",
    esri: 'http://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
    ht: 'http://www.oceanread.com:213/arcgis/rest/services/chart/chartAll/MapServer/tile/{z}/{y}/{x}',
    TDT: 'http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer/tile/{z}/{y}/{x}', //http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer/tile/{z}/{y}/{x}
    TDTI: 'https://t{s}.tianditu.gov.cn/img_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=img&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&tk=0c005ba20f7255ad23ebd218a6f2acdd',
    TDTV: 'https://t{s}.tianditu.gov.cn/vec_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&tk=0c005ba20f7255ad23ebd218a6f2acdd',
    TDTW: 'https://t{s}.tianditu.gov.cn/cva_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cva&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&tk=0c005ba20f7255ad23ebd218a6f2acdd',
    esritest: 'http://localhost:8099/{z}/{x}/{y}.png',
};
let BaseLayers = {
    esritest: new maptalks.TileLayer('esritest', {
        'urlTemplate': Tile.esritest,
        'subdomains': ['a', 'b', 'c'],
        maxAvailableZoom:12,
        debug:true
    }),
    tdti: new maptalks.GroupTileLayer('group', [
        new maptalks.TileLayer('TDTI', {
            'urlTemplate': Tile.TDTI,
            'subdomains': ['0', '1', '2', '3', '4', '5', '6', '7']
        }),
        /* new maptalks.TileLayer('TDTW', {
          'urlTemplate': Tile.TDTW,
          'subdomains': ['0', '1', '2', '3', '4', '5', '6', '7']
        }) */
    ]),
    tdtv: new maptalks.GroupTileLayer('group', [
        new maptalks.TileLayer('TDTV', {
            'urlTemplate': Tile.TDTV,
            'subdomains': ['0', '1', '2', '3', '4', '5', '6', '7']
        }),
        new maptalks.TileLayer('TDTW', {
            'urlTemplate': Tile.TDTW,
            'subdomains': ['0', '1', '2', '3', '4', '5', '6', '7']
        })
    ]),
    esri: new maptalks.TileLayer('esri', {
        'urlTemplate': Tile.esri,
        'subdomains': ['a', 'b', 'c']
    }),
    ht: new maptalks.TileLayer('ht', {
        'urlTemplate': Tile.ht,
        'subdomains': ['a', 'b', 'c']
    }),
    GeoQ: new maptalks.TileLayer('GeoQ', {
        'urlTemplate': Tile.layerGeoQ,
        'subdomains': ['a', 'b', 'c'],
        //cssFilter : ' brightness(200%)'
    }),
};
let ScaleConf = {
    'position': {
        'bottom': '25',
        'left': '2'
    },
    'maxWidth': 150,
    'metric': true,
    'imperial': false,
    //containerClass:'map-scale'
};
let drawAltitude = {
    lineWidth: 1,
    lineColor: '#000'
};
let layerConf = {
    "seaArea": {
        name: '海区',
        code: 'seaArea',
        label: 'organName',
        title: 'organName',
        pic: 'fileId',
        layerType: 'other',
        check: false,
        btn: [{
            type: "org_detail",
            name: '百科'
        }],
        column: [{
                name: '所在区域',
                key: 'areaName',
                unit: ''
            },
            {
                name: '站点简介',
                key: 'briefOrg',
                unit: '',
                info: 'full'
            }
        ]
    },
};
let center = [120.293, 36.095];
let zoom = 10;
let eyeCenter = [120, 35.8];
let eyeZoom = 8;
let other = {
    "groupLayers": [{
        name: "海湾线",
        check: false
    }, {
        name: "海湾点",
        check: false
    }, {
        name: "海湾标注",
        check: false
    }],
};

let video1Conf = {
    code:"01",
    name:"岸基一号视频",
    src:"http://120.224.102.163:8012/test.m3u8"
}
let video2Conf = {
    code:"02",
    name:"岸基二号视频",
    src:"http://120.224.102.163:8012/test.m3u8"
}


export {
    trendNodes,
    DistanceToolConf,
    AreaToolConf,
    Tile,
    ScaleConf,
    layerConf,
    center,
    zoom,
    drawAltitude,
    eyeCenter,
    eyeZoom,
    other,
    value,
    BaseLayers,
    video1Conf,video2Conf
}