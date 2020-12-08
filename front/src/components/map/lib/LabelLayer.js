import * as maptalks from 'maptalks';

export default class LabelLayer {

    /**
     * 构造方法 
     */
    constructor(map, { id = "default", }) {

        let that = this;
        this.map = map;
        this.markers = [];
        this.layer = new maptalks.VectorLayer("labelLayer_" + id, {
            enableAltitude: false,
        }).addTo(map);

        map.on('zoomend', (ctx) => {
            this.onZoomend(ctx);
        });


    }
    //box-shadow: -1px 0px 4px #02ffff, 0px -1px 4px #02ffff, 1px 0px 4px #02ffff, 0px 1px 4px #02ffff;


    addMarker(lonlat, { id, name = "名称", icon = "icon iconfont_map icon_map-info", iconType = "class", minZoom = 1, maxZoom = 18, guidH = 20, labelSize = 34 }) {
        if (!id) {
            console.log("none id, canot build query")
        }
        let fontSize = (labelSize - 18) > 12 ? labelSize - 18 : 12;
        let nameLength = name.length * fontSize + 10;
        let labelLength = labelSize + nameLength + 10;
        let labelHeight = guidH + labelSize;
        var dom = document.createElement('div');
        if (iconType == "none") {
            dom.innerHTML = `
            <div  style="width:${labelLength}px;height:${labelHeight}px;position:relative;">
            <div class="main-label" style = "position: absolute;left: 0;top: 0px;height: ${labelSize}px;width: 100%;background: #0378922e;font-size: ${fontSize}px;line-height: ${labelSize}px;">
                <div style="float:left;padding:0 0 0 5px">${name}</div>
            </div>
            <div class="label-guid" style="position: absolute;left: ${labelSize/2}px;top: ${labelSize}px;height: ${guidH}px;width: 1px;border-left: 1px #16c8fd solid;"></div>
            </div>`;
        } else {
            dom.innerHTML = `
            <div  style="width:${labelLength}px;height:${labelHeight}px;position:relative;">
            <div class="main-label" style = "position: absolute;left: 0;top: 0px;height: ${labelSize}px;width: 100%;background: #0378922e;font-size: ${fontSize}px;line-height: ${labelSize}px;">
                <div style="float:left;position:relative;width:${labelSize}px;height:${labelSize}px">
                    <span style="font-size:${labelSize - 8}px" class="${icon}"></span>
                    <li  class="left-bottom" style="border:1px solid #02ffff;border-right: none;border-top: none;bottom: 0px;left: 0px;height: 8px;position: absolute;width: 8px;"></li> 
                    <li  class="left-top" style="border:1px solid #02ffff;border-right: none;border-bottom: none;top: 0px;left: 0px;height: 8px;position: absolute;width: 8px;"></li> 
                    <li class="right-bottom" style="border:1px solid #02ffff;border-left: none;border-top: none;bottom: 0px;right: 0px;height: 8px;position: absolute;width: 8px;"></li> 
                    <li  class="right-top" style="border:1px solid #02ffff;border-left: none;border-bottom: none;top: 0px;right: 0px;height: 8px;position: absolute;width: 8px;"></li>
                </div>
                <div style="float:left;padding:0 0 0 5px">${name}</div>
            </div>
            <div class="label-guid" style="position: absolute;left: ${labelSize/2}px;top: ${labelSize}px;height: ${guidH}px;width: 1px;border-left: 1px #16c8fd solid;"></div>
            </div>`;
        }

        var marker = new maptalks.ui.UIMarker(lonlat, {
            draggable: false,
            content: dom,
            dx: labelLength / 2 - labelSize / 2,
            dy: -labelHeight / 2
        }).addTo(this.map);
        marker.minZoom = minZoom;
        marker.maxZoom = maxZoom;
        marker.show();
        this.markers.push(marker)
        return marker;
    }

    onZoomend(zoom) {
        this.markers.forEach((marker) => {
            if (marker.minZoom <= zoom.to && marker.maxZoom >= zoom.to) {
                if (!marker.isVisible()) {
                    marker.show()
                }
            } else {
                marker.hide()
            }
        })
    }



}