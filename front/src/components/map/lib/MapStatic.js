let MousePosConf = {
    position: {
        'bottom': '0',
        'left': '0'
    }
};
let ScaleConf = {
    'position': {
        'bottom': '5',
        'right': '5'
    },
    'maxWidth': 150,
    'metric': true,
    'imperial': false,
    containerClass: 'mtk-scale'
};
let OverviewConf = {
    maximize: false,
    size: [210, 140],
    position: {
        'top': '100',
        'right': '6'
    }
};
let ZoomConf = {
    maximize: false,
    size: [210, 140],
    position: {
        'top': '110',
        'right': '0'
    }
};
let DistenceConf = {
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
let AreaConf = {
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

export { MousePosConf, ScaleConf,OverviewConf, DistenceConf, AreaConf ,ZoomConf}