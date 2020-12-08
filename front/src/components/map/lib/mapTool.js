import {
  polygon,
  pointOnFeature,
  centroid
} from '@turf/turf'
import * as maptalks from 'maptalks';

let maptool = {};
maptool.getAISPoint = function (angle, datetime, level, type) {
  const rotate = level && level > 10 ? true : false;
  let symbol = {
    markerType: rotate ? 'triangle' : 'ellipse',
    markerFill: fillColor,
    markerLineColor: rotate ? '#000000' : '#ffffff',
    markerLineWidth: rotate ? 1 : 0,
    markerWidth: rotate ? 10 : 5,
    markerHeight: rotate ? 20 : 5,
    markerRotation: 0
  }
  let fillColor = '#0000ff'
  if (typeof key === 'object') {
    const gSymbol = key.getSymbol()
    symbol.markerFill = gSymbol.markerFill
    symbol.markerRotation = gSymbol.markerRotation
  } else {
    const current = new Date().getTime()
    const updateDate = new Date(datetime).getTime()
    const between = current - updateDate
    if (between < 1000 * 60 * 30) {
      switch (type) {
        case 'enter':
          symbol.markerFill = '#ff003b'
          break
        case 'experiment':
          symbol.markerFill = '#3366cc'
          break
        default:
          symbol.markerFill = '#f9f1da'
      }
    } else {
      symbol.markerFill = '#f9f1da'
    }
    symbol.markerRotation = Number(angle)
  }
  return symbol
}
maptool.getPointStyle = function (conf) {
    let src = conf.src;
    let dx = conf.dx || 0;
    let dy = conf.dy || 0;
    let width = conf.width || 20;
    let height = conf.height || 30;
    let textDx = conf.textDx || 10;
    let textDy = conf.textDy || 3;
    let isLabel = !conf.label || conf.label === '' ? false : true;
    let label = conf.label;
    let symbol = [{
      'markerFile': src,
      'markerWidth': width,
      'markerHeight': height,
      'markerDx': dx,
      'markerDy': dy,
    }];
    if (isLabel) {
      symbol.push({
        'textName': '{' + label + '}',
        'textSize': 12,
        'textFill': '#02F1F3',
        textWeight: 400,
        textHorizontalAlignment: 'right',
        textHaloFill: '#0e56a8',
        textHaloRadius: 1,
        textDy: textDy,
        textDx: textDx,
      });
    }
    return symbol;
  },
  maptool.getDoubleLabelStyle = function (conf) { //用于单点两个标注：海湾标注
    let textDx1 = conf.textDx1 || 0;
    let textDy1 = conf.textDy1 || 3;
    let textDx2 = conf.textDx2 || 0;
    let textDy2 = conf.textDy2 || -33;
    let label1 = conf.label1;
    let label2 = conf.label2;
    let s1 = {
      'textName': '{' + label1 + '}',
      'textSize': 18,
      textWeight: 400,
      'textFill': '#101010',
      'textHaloFill': '#fff',
      'textHorizontalAlignment': 'middle',
      'textHaloRadius': 3,
      textDy: textDy1,
      textDx: textDx1,
    };
    let s2 = {
      'textName': '{' + label2 + '}',
      'textSize': 12,
      textWeight: 200,
      'textFill': '#101010',
      'textHaloFill': '#fff',
      'textHaloRadius': 1,
      'textHorizontalAlignment': 'middle',
      textDy: textDy2,
      textDx: textDx2,
    };
    return [s1, s2];
  },

  maptool.getLabelStyle = function (conf) { //单点标注：海湾标注
    let textDx = conf.textDx || 0;
    let textDy = conf.textDy || 3;
    let key = conf.key;
    let symbol = {
      'textName': '{' + key + '}',
      'textSize': 14,
      textWeight: 400,
      'textFill': '#fff',
      'textHaloFill': '#101010',
      'textHorizontalAlignment': 'middle',
      'textHaloRadius': 1,
      textDy: textDy,
      textDx: textDx,
    };
    return symbol;
  },
  maptool.getLineSymbol = function (color, alpha) {
    color = color || '#ffffff';
    alpha = alpha || 1;
    let symbol = {
      'lineColor': color,
      'lineWidth': 2,
      'lineDasharray': null, //线形
      'lineOpacity': alpha
    };
    return symbol;
  };
maptool.getPatternSymbol = function (pattern, fillAlpha, key, lineColor, dash) { //图案base64填充
  let alpha = fillAlpha || 0.8;
  let line = lineColor || '#121212';
  let symbol = [];
  var poly = {
    'polygonOpacity': alpha,
    'polygonPatternFile': pattern,
    lineColor: line,
    'lineWidth': 0.5,
    lineOpacity: 0.8
  };
  if (dash) {
    poly.lineDasharray = dash;
  }
  symbol.push(poly);
  if (key) {
    symbol.push({
      'textName': '{' + key + '}',
      'textSize': 14,
      'textFill': '#fff',
      'textHaloFill': '#101010',
      'textHaloRadius': 2,
      'textHorizontalAlignment': 'middle',
      textWeight: 400,
    });
  }
  return symbol;
};
maptool.getFillSymbol = function (color, lineColor, textColor, key, fillalpha, borderAlpha) {
  let symbol = [];
  borderAlpha = borderAlpha ? borderAlpha : 0.8;
  fillalpha = fillalpha || fillalpha == 0 ? fillalpha : 0.8;;
  lineColor = lineColor || lineColor == 0 ? lineColor : '#104068';
  textColor = textColor ? textColor : '#FFFFFF';
  symbol.push({
    'polygonFill': color,
    'polygonOpacity': fillalpha,
    'lineColor': lineColor,
    'lineWidth': 0.5,
    lineOpacity: borderAlpha
  });
  if (key && key !== '') {
    symbol.push({
      'textName': '{' + key + '}',
      'textSize': 14,
      'textFill': '#fff',
      'textHaloFill': '#101010',
      'textHaloRadius': 2,
      'textHorizontalAlignment': 'middle',
      textWeight: 400,
    });
  }
  return symbol;
};

maptool.numLabelSymbol = function (conf) {
  let color = conf.color || '#f41261' ;
  let symbol = [];
  symbol = [{
      'markerType': 'ellipse',
      'markerFill': color,
      'markerFillOpacity': 0.8,
      'markerWidth': 30,
      'markerHeight': 30,
      'markerLineWidth': 0
    },
    {
      'markerType': 'ellipse',
      'markerFill': color,
      'markerFillOpacity': 0.7,
      'markerWidth': 40,
      'markerHeight': 40,
      'markerLineWidth': 0
    },
    {
      'markerType': 'ellipse',
      'markerFill': color,
      'markerFillOpacity': 0.6,
      'markerWidth': 50,
      'markerHeight': 50,
      'markerLineWidth': 0
    },
    {
      'markerType': 'ellipse',
      'markerFill': color,
      'markerFillOpacity': 0.3,
      'markerWidth': 60,
      'markerHeight': 60,
      'markerLineWidth': 0,
      'textFill': '#34495e',
      'textOpacity': 1,
      'textHaloFill': '#fff',
      'textHaloRadius': 1,
      'textFaceName': 'sans-serif',
      'textName': '{organName}',
      'textDy'   : 18,
      'textSize': 12,
    },
    {
      'markerType': 'ellipse',
      'markerFill': color,
      'markerFillOpacity': 0.2,
      'markerWidth': 80,
      'markerHeight': 80,
      'textFill': '#34495e',
      'textOpacity': 1,
      'textHaloFill': '#fff',
      'textHaloRadius': 3,
      'textFaceName': 'sans-serif',
      'textName': '{num}',
      'textDy'   : 2,
      'textSize': 18,
      'markerLineWidth': 0
    }
  ]
  return symbol;
};
maptool.getStyleFromConf = function (conf, label) { //style-conf   图上标注label（可以没有）
  let style = [];
  let that = this;
  if (!conf) {
    return [];
  }
  conf.forEach(function (item) {
    let fillType = item.fillType ? item.fillType : item.type;
    let borderColor = item.borderColor ? item.borderColor : undefined;
    let textColor = item.textColor ? item.textColor : undefined;
    let symbol;
    let borderAlpha = item.borderAlpha ? item.borderAlpha : 0.9;
    let fillAlpha = item.fillAlpha ? item.fillAlpha : 0.9;
    if (fillType === 'fill') {
      symbol = maptool.getFillSymbol(item.color, borderColor, textColor, false, fillAlpha, borderAlpha);
    }
    if (fillType === 'bias') {
      symbol = maptool.getPatternSymbol(item.src, item.alpha, false, item.borderColor, item.lineDash);
    }
    style.push({
      'filter': ['==', item.key, item.value],
      'symbol': symbol
    })
  });
  return style;
};

maptool.labelonLayer = function (layer, list, key) {
  list.forEach(function (json) {
    var geojson = JSON.parse(json.geo);
    maptalks.GeoJSON.toGeometry(geojson, geo => {
      var point = pointOnFeature(polygon(geojson.coordinates));
      let label = new maptalks.Marker(point.geometry.coordinates);
      label.setProperties(json);
      label.setSymbol(maptool.getLabelStyle({
        key: key
      })).addTo(layer);
    });
  })
};
maptool.getLineStyleFromConf = function (conf) { //style-conf   图上标注label（可以没有）
  let style = [];
  let that = this;
  conf.forEach(function (item) {
    var symbol = maptool.getLineConfSymbol(item);
    style.push({
      'filter': ['==', item.key, item.value],
      'symbol': symbol
    })
  });
  return style;
};
maptool.getLineConfSymbol = function (conf) {
  let color = conf.color || '#ffffff';
  let dashArray = !conf.dashArray ? null : conf.dashArray;
  let symbol = {
    'lineColor': color,
    'lineWidth': 3,
    'lineDasharray': dashArray, //线形
    'lineOpacity': 1
  };
  return symbol;
};
maptool.panelHtml = function (geos, conf) {
  var html = '';
  html += '<div class="map-panel">';
  html += '<div class="popup-left relativeContianer left">';
  geos.forEach((geo) => {
    var attr = geo.getProperties();
    html += '<div class="panel-row left bootstrap">';
    html += '<div class="panel-label ellipse left">' + attr[conf.name] + '</div>';
    html += '<div class="panel-data left">' + "<a  class='panel-btn left' href='javascript:void(0);' onclick=\"popupClick('卫士','" + attr[conf.key] + "');\">" + conf.btn + "</a>" + ' </div></div>';
  });
  html += '</div></div>';
  return html;
}

maptool.wsPopHtml = function (geos, conf) {
  var html = '';
  html += '<div class="map-panel">';
  html += '<div class="popup-left relativeContianer left">';
  geos.forEach((geo) => {
    var attr = geo.getProperties();
    html += '<div class="panel-row left bootstrap">';
    html += '<div class="panel-label ellipse left">' + attr[conf.name] + '</div>';
    html += '<div class="panel-data left">' + "<a  class='panel-btn left' href='javascript:void(0);' onclick=\"popupClick('卫士','" + attr[conf.key] + "');\">" + conf.btn + "</a>" + ' </div></div>';
  });
  html += '</div></div>';
  let html_title = '<div class="popup-title ellipse left">[海洋卫士] 监控列表</div>';
  return {
    autoOpenOn: false,
    title: html_title,
    content: html
  };
};

maptool.ecDefault = {
  series: []
}
/* 生成观测力量option */
maptool.getPowerEC = function (data) {
  var seaArea = data;
  var centerStationList = data.children;
  var area_center = [];
  var seaAreaCoords = [seaArea.attributes.lon, seaArea.attributes.lat];
  var center_sea = {};
  centerStationList.forEach((centerStation) => {
    var centerName = centerStation.attributes.organName;
    var centerStationCoords = [centerStation.attributes.lon, centerStation.attributes.lat];
    area_center.push({
      fromName: seaArea.attributes.organName,
      toName: centerName,
      coords: [
        seaAreaCoords,
        centerStationCoords
      ]
    })
    if (centerStation.children instanceof Array && centerStation.children.length > 0) {
      var center_seaList = [];
      var seaStationList = centerStation.children;
      seaStationList.forEach((seaStation) => {
        var seaName = seaStation.attributes.organName;
        var seaStationCoords = [seaStation.attributes.lon, seaStation.attributes.lat];
        center_seaList.push({
          fromName: centerName,
          toName: seaName,
          coords: [
            centerStationCoords,
            seaStationCoords
          ]
        })
      });
      center_sea[centerName] = center_seaList;
    }
  });
  return maptool.setEC(data, area_center, center_sea);
}
/* odline形式生成观测力量 */
maptool.getPowerODLine = function (data) {
  var seaArea = data;
  var centerStationList = data.children;
  var area_center = [];
  var seaAreaCoords = [seaArea.attributes.lon, seaArea.attributes.lat];
  var center_sea = [];
  centerStationList.forEach((centerStation) => {
    var centerName = centerStation.attributes.organName;
    var centerStationCoords = [centerStation.attributes.lon, centerStation.attributes.lat];
    area_center.push({
      coordinates: [seaAreaCoords,centerStationCoords]
    })
    if (centerStation.children instanceof Array && centerStation.children.length > 0) {
      var center_seaList = [];
      var seaStationList = centerStation.children;
      seaStationList.forEach((seaStation) => {
        var seaName = seaStation.attributes.organName;
        var seaStationCoords = [seaStation.attributes.lon, seaStation.attributes.lat];
        center_seaList.push({
          coordinates: [centerStationCoords,seaStationCoords]
        })
      });
      center_sea = center_sea.concat(center_seaList);
    }
  });
  return {
    area_center:area_center,
    center_sea:center_sea
  };
}
maptool.setEC = function (data, area_center, center_sea) {
  var color = ['#a6c84c', '#ffa022', '#46bee9'];
  var series = [];
  series.push({
    name: '分局-中心站-粒子',
    type: 'lines',
    zlevel: 2,
    effect: {
      show: true,
      period: 5,
      trailLength: 0.5,
      color: '#fff',
      symbolSize: 8
    },
    lineStyle: {
      normal: {
        color: "#35b2ce00",
        width: 4,
        curveness: 0.15
      }
    },
    data: area_center
  });
  series.push({
    name: '分局-中心站-底线',
    type: 'lines',
    zlevel: 1,
    effect: {
      show: true,
      period: 6,
      trailLength: 0,
      symbol: "emptyCircle",
      symbolSize: function (v) {
        return 15 + v / 10;
      }
    },
    lineStyle: {
      normal: {
        color: '#2ec0ec',
        width: 6,
        opacity: 0.8,
        curveness: 0.15
      }
    },
    data: area_center
  });
  series.push({
    name: "北海局符号",
    type: 'effectScatter',
    coordinateSystem: 'geo',
    zlevel: 2,
    rippleEffect: {
      brushType: 'stroke'
    },
    label: {
      normal: {
        show: true,
        color: "#fff",
        backgroundColor: '#73d1e699',
        padding: 5,
        borderRadius: 2,
        position: 'left',
        formatter: '{b}'
      }
    },
    symbolSize: function (val) {
      return 20;
    },
    itemStyle: {
      normal: {
        color: '#2ec0ec'
      }
    },
    data: [{
      name: data.attributes.organName,
      value: [data.attributes.lon, data.attributes.lat]
    }]
  });
  var center_names = [];
  area_center.forEach(function (item) {
    center_names.push({
      name: item.toName,
      value: item.coords[1]
    })
  })
  series.push({
    name: "中心站符号",
    type: 'effectScatter',
    coordinateSystem: 'geo',
    zlevel: 2,
    rippleEffect: {
      brushType: 'stroke'
    },
    label: {
      normal: {
        show: true,
        color: "#fff",
        borderRadius: 2,
        backgroundColor: '#73d1e699',
        padding: 4,
        position: 'right',
        formatter: '{b}'
      }
    },
    symbolSize: function (val) {
      return 10;
    },
    itemStyle: {
      normal: {
        color: '#ecc32e'
      }
    },
    data: center_names
  });
  Object.keys(center_sea).forEach(function (key) {
    series.push({
      name: key + '底线',
      type: 'lines',
      zlevel: 1,
      effect: {
        show: true,
        period: 6,
        trailLength: 0,
        symbol: "emptyCircle",
        symbolSize: function (v) {
          return 10 + v / 10;
        }
      },
      lineStyle: {
        normal: {
          color: '#ecc32e',
          type: 'dashed',
          width: 2,
          opacity: 0.4,
          curveness: 0.1
        }
      },
      data: center_sea[key]
    });
  })
  return {
    tooltip: {
      show: false
    },
    series: series
  }
};

maptool.favSymbol = [{
  'markerFile': "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAfCAYAAAD9cg1AAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAACcQAAAnEAGUaVEZAAAAB3RJTUUH4wERFzMwQho6rwAABgVJREFUSMeNln9sXWUZxz/Pe857zr3n/mjXdesoUAuIA6fofoRukzCXxSxNxJjQjs2ARBIp/0xCYkT/UCNCBMMS7TYCIWIC8ReKU8FMp9Mo0ykiwYUgG4MMuxVaRlvb3t4f55z38Y9773a3tavPf+ec93m+3+fH93mPMI9Nb99BGsf1BxHCbJZsZwf09sLISSiX53ND9u698N35LyYHBkEVgJn3JrzC0o6rbCZcY8PwWs/aTuN5KiLvAq8BLwFvAulCIGcAZj9zK3G1gqpy7IUX+cD16/psGAwF2ewn/CDoFmOMyDl8HDAKHAAeB/52Nqoge/acBUhuv53p2VKduWpOjNkZZLP3hLlouef7rUEbdcOel/g48B1gGCgBYAyyezc+wMxsCQEUCmLMg2EuuiuTzzcZjwIHEQ4BI/XyyeXADcAWoBtYDtwPXA7cC8zgXD2DqcFtaL3mnjp3XxhFX8kWCyIiCfAj4OHxSu2VLW/PXbOiWNgY5XJpyZjnf5+dfoPU/zDwRWB7IysFHgS+2uyLcUmCOoc61+9ZuzOTz4mIxI2DQ8djd2Tb2NyOnmL+wJJc9HjWyBOd6IFbyoWbDjvvCDAEPNQonwA7gf5m7YwYg4gUELk7jLIF43kAPwAe2D1RLt9xcqrXWvv1KIouFWNwqqhyBfCN4SToeib1y8ADDR+APHA3UAAwqoqq9nmet9GGIcAI8DBQ+fFUhcTp1Z7v9wTWp5DxWXNlO/1ru4hC76pqolf8MLEAlYbPyQbIRuB6AF+dQ4zZ7FkbNdj/DngVYP37QjzR3MrLCt4HV15Ce0eRrrYQ3zO0+1X/tRMTOTECE9DwOQDcAUTAx4GDvmdt4NJ0lWfPjONfG83iCzcWEbgsXyz4mWU5NIpwri7Cj/WYcMPSsDt1KfwRGj6HGwAAqwBr1LkM0GmMac75200Z+UYQdT8X8Z5MnTYnDwAvm3tUq6XfGBTOCnC0RSvLgIzRC1eGV28/qCq5tqWjNgi/S1w7habNo8eDjq7hTM/Kd+vc9VzfszHFiEgVmNJ66hboBVBrkS0PUfvInYy1971UmxofTifGoDyDlKZ2z6RdR8vvv430xm+i9oywe1tUPglUTVqLq8DRNEmahzYhYk8N3kaaJmi2M6sr1q6fWn7D6niujCuVmIyuWz+7ZPV6vEwW4PStQyBigU0tGRwFqsb4HsCfkziOtV7kzaRp3+TqNUsFGXLO7bNG9yedH9peWbaOmeIq5vJX7gg82e+c2wfcObv2mg7StA/Y3AheA54H8EUEVT3kkuRfca22LshkOlwQ3F98482KdF26FcDzA3xjqfzpdWxosZ9ajecH7cBWxGzNHh/7tAZhJEnc0QB4GTgEYNo3bEBVT6vqI9W5uVidwyTJps5Df9jqz0zXt6IxhLkiubhGFMeEuQJiDBiDNz1F8eCv+yVJmuWJgUeA9xDBu6etvd5ukWMudT0gH7VhQDD+DsHJt9Awi8sXwFrCl18gCANq163FlOfIHPs3bb/9JZnXX20pPU8B3waSM+M5efNAc6N2izGPZQv5T4ZRBKqotcRd3cQrupFXjuCHAfHV12LfOYUdG0XiGohpBv8VcFdTS7J3b/0+8HyfpH4Hj2qafr4yM3ufOv1sGGVDSRKCkRMEIyfAGKiWCP7xl8aqNM3gFeBJ4GvAWGs6ZwT232230BxVl6ah5/ufs2H4rWyx0N7YUQvZFPBl4PuN6aHJvqHXurU9/RNsECAieL5fNb7/aK1S2VOemW1V6nw2DDw2X/BzMmi1yZsHAFDVbhF5NtfetsZmMvMd/SdwU2vNzzczn9eSZ35GEtdAdVRVd1VKc1Vt3XR1qwK7LhZ8QQAAG2Zo3Hb7klrtuercBT9bzwK/YBFbEKD9p0+jKKpaBnZVy3PjLftqjPoNVr4Y+4sCAHi+RYyhbfmywy5Jn6iWSs1P3yNN/74Ye1igya02OTAACqraI8bsj4oFDfL5ftJ0ZDH2i2YAEEQRLz53EHX6H3XuS+WZ2XtPn3hrhItr4//PAGBqcBBEcEmKHwQULumCWrwoe4D/Ac81VlEleuELAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE5LTAxLTE3VDIzOjUxOjQ4KzA4OjAwyY+pDAAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxOS0wMS0xN1QyMzo1MTo0OCswODowMLjSEbAAAABDdEVYdHNvZnR3YXJlAC91c3IvbG9jYWwvaW1hZ2VtYWdpY2svc2hhcmUvZG9jL0ltYWdlTWFnaWNrLTcvL2luZGV4Lmh0bWy9tXkKAAAAGHRFWHRUaHVtYjo6RG9jdW1lbnQ6OlBhZ2VzADGn/7svAAAAGHRFWHRUaHVtYjo6SW1hZ2U6OkhlaWdodAA2MjU4giu4AAAAF3RFWHRUaHVtYjo6SW1hZ2U6OldpZHRoADQ3NzuOOuIAAAAZdEVYdFRodW1iOjpNaW1ldHlwZQBpbWFnZS9wbmc/slZOAAAAF3RFWHRUaHVtYjo6TVRpbWUAMTU0Nzc0MDMwOJ5308EAAAASdEVYdFRodW1iOjpTaXplADMzNzU2QgsNVDYAAABidEVYdFRodW1iOjpVUkkAZmlsZTovLy9ob21lL3d3d3Jvb3QvbmV3c2l0ZS93d3cuZWFzeWljb24ubmV0L2Nkbi1pbWcuZWFzeWljb24uY24vZmlsZXMvMTIyLzEyMjc5NDAucG5nQeWGKgAAAABJRU5ErkJggg==",
  'markerWidth': 20,
  'markerHeight': 30,
  'markerDx': 0,
  'markerDy': 0,
}, {
  'textName': '{name}',
  'textSize': 12,
  'textFill': '#02F1F3',
  textWeight: 400,
  textHorizontalAlignment: 'right',
  textHaloFill: '#0e56a8',
  textHaloRadius: 1,
  textDy: 3,
  textDx: 10,
}];

maptool.getBasePointStyle = function(icon,labelText) {
  let symbol = [{
    'markerFile': icon,
    'markerWidth': 20,
    'markerHeight': 30,
    'markerDx': 0,
    'markerDy': 0,
  }, {
    'textName': '{' + labelText + '}',
    'textSize': 12,
    'textFill': '#fff',
    textWeight: 400,
    textHorizontalAlignment: 'right',
    textHaloFill: '#257284',
    textHaloRadius: 2,
    textDy: 3,
    textDx: 10,
  }];
  return symbol;
}

export default maptool
