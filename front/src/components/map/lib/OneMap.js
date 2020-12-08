import * as maptalks from 'maptalks';
import * as THREE from 'three';
import {  mtkTool, MousePos, MaskControl } from "typical-mtk-onemap";
import { Stack } from "typical-base"
import { ThreeLayer } from 'maptalks.three';

import Popup from "@/components/map/lib/Popup"


//import Popup from "@/components/map/lib/Popup"
//import popbg from "@/components/map/pic/popup_pane.png"
let { base, StaticConf } = mtkTool
let _layerTDT = base.TileLayer["TDT.Normal"],
    _layerGeoQ = base.TileLayer["geoq.blue"],
    _layerBlue = base.TileLayer["carto.blue"],
    _layerImage = base.TileLayer["TDT.Satellite"],
    _layerHT = base.TileLayer["ht"];

export default class OneMap {

    constructor(div, {
        center = [122.079, 37.588],
        zoom = 12,
        maxZoom,
        minZoom,
        bearing = 0,
        pitch,
        zoomControl = false,
        scale,
        overviewControl,
        mousePos = true,
        mask = false,
        onMouseMove,
        threeReady,
        fog = [52, 69, 132],
        topicStatusChange,
    }) {
        let that = this;
        let map = this.map = new maptalks.Map(div, {
            center: center,
            zoom: zoom,
            bearing: bearing,
            maxZoom: maxZoom ? maxZoom : zoom + 6,
            minZoom: minZoom ? minZoom : 2,
            fogColor: fog,
            attribution: "",
        });
        if (pitch)
            map.setPitch(pitch);

        if (mask) {
            map.addControl(new MaskControl({
                url: "/static/base/map/border-mask.png",
                css: "width:100vw;height:100vh;",
                opacity: 0.7
            }));
        }

        if(zoomControl){
            zoomControl.position = zoomControl.position || {
                'bottom': '0',
                'right': '0'
            };
            map.addControl(new maptalks.control.Zoom({
                position: zoomControl.position
            }));
        }
        
        if (scale) {
            map.addControl(new maptalks.control.Scale(scale));
        }

        if(overviewControl){
            overviewControl.position = overviewControl.position || {
                'bottom': '0',
                'right': '0'
            };
            map.addControl(new maptalks.control.Overview({
                maximize: false,
                size: [210, 140],
                position: overviewControl.position
            }));
        }

        if (mousePos)
            map.addControl(new MousePos(mousePos));

        if (topicStatusChange && typeof topicStatusChange == 'function') {
            this.topicStatusChange = topicStatusChange
        } else {
            this.topicStatusChange = function() {

            }
        }

        map.on('mousemove', function(evt) {
            if (onMouseMove && typeof onMouseMove == 'function') {
                onMouseMove(evt)
            }
        });

        this.distanceTool = new maptalks.DistanceTool(StaticConf.DistanceToolConf).addTo(map);
        this.distanceTool.disable();
        this.areaTool = new maptalks.AreaTool(StaticConf.AreaToolConf).addTo(map);
        this.areaTool.disable();

        this.areaTool.on('drawend', function(param) {

        });

        this.topics = {}

        this.initThreeLayer().then((threeLayer) => {
            this.threeLayer = threeLayer
            this.threeLayer.addTo(map);
        })

        this.popup = new Popup(map, { initWidth: 400, bgImg: '/static/base/map/popbg.png' });

        this.BaseLayerHistory = new Stack(5)
        this.TopicLayerHistory = new Stack(5)

    }

    initThreeLayer() {
        return new Promise((resolve, reject) => {
            let threeLayer = new ThreeLayer('three');
            ThreeLayer.prototype.coordinateToXYZ = function(coordinate, height = 0) {
                const z = this.distanceToVector3(height, height).x;
                return this.coordinateToVector3(coordinate, z);
            }
            threeLayer.prepareToDraw = function(gl, scene, camera) {
                const light = new THREE.DirectionalLight(0xffffff);
                light.position.set(0, 10, 10).normalize();
                scene.add(new THREE.AmbientLight("#fff"));
                scene.add(light);
                resolve(threeLayer)
            };
            threeLayer.config('animation', true);
        })
    }

    locate(center, zoom) {
        this.map.animateTo({
            center: center,
            zoom: zoom,
        });
    }

    setBaseLayer(layer) {
        switch (layer) {
            case '地图':
                this.map.setBaseLayer(_layerTDT);
                this.BaseLayerHistory.push(layer);
                break;
            case '遥感':
                this.map.setBaseLayer(_layerImage);
                this.BaseLayerHistory.push(layer);
                break;
            case '海图':
                this.map.setBaseLayer(_layerHT);
                this.BaseLayerHistory.push(layer);
                break;
            case '深色':
                this.map.setBaseLayer(_layerBlue);
                this.BaseLayerHistory.push(layer);
                break;
            default:
                break;
        }
    }

    setBaseLayerBack() {
        this.BaseLayerHistory.pop()
        let layer = this.BaseLayerHistory.pop()
        if (layer) {
            this.setBaseLayer(layer)
        }
    }

    setTopicLayerBack() {
        let layer = this.TopicLayerHistory.pop()
        if (layer) {
            this.toggleTopic(layer, true)
        }
    }

    /**
     * 加载对象（广义）的统一框架
     * @param {} param0 
     */
    addThing({
        name,
        onPrepare,
        onAdd,
        onShow,
        onHide,
        onRemove,
        onZoomend,
        lazy = true
    }) {
        let _obj = {
            visible: false,
            init: false,
        };
        _obj.statusChange = function() {
            that.topicStatusChange(_obj)
        }
        let that = this;
        if (!name) {
            console.error('工作空间中未设置标识名称');
            return;
        }
        if (!(onAdd && typeof onAdd == 'function')) {
            console.error('未定义' + name + "-onadd方法");
            return;
        }
        if (!(onShow && typeof onShow == 'function')) {
            console.error('未定义' + name + "-onShow方法");
            return;
        }
        if (!(onHide && typeof onHide == 'function')) {
            console.error('未定义' + name + "-onHide方法");
            return;
        }
        _obj.name = name;
        if (onPrepare && typeof(onPrepare) == 'function') {
            onPrepare(_obj, that);
        }
        _obj.show = () => {
            if (!_obj.init || !_obj.lazy) {
                onAdd(_obj, that);
                _obj.init = true;
            }
            onShow(_obj, that);
            _obj.visible = true;
            _obj.statusChange()
        }
        _obj.hide = () => {
            onHide(_obj, that);
            _obj.visible = false;
            _obj.statusChange()
            that.popup.checkClose(_obj.name)
        };
        _obj.remove = () => {
            if (onRemove && typeof onRemove == 'function')
                onRemove(_obj, that);
            delete that.topics[name]
            _obj = null;
        };
        that.topics[name] = _obj;
        return _obj;
    }

    /**
     * 加载基础专题图
     * @param {} param0 
     */
    addBaseTopic({
        name,
        onPrepare,
        onAdd,
        onShow,
        onHide,
        onRemove,
        onZoomend,
        lazy = true
    }) {
        let _obj = {
            name: name,
            visible: false,
            init: false,
            lazy: lazy
        };
        let that = this;
        _obj.statusChange = function() {
            that.topicStatusChange(_obj)
        }
        if (!name) {
            console.error('工作空间中未设置标识名称');
            return;
        }
        _obj.layer = new maptalks.VectorLayer(name).addTo(that.map).hide();
        if (!(onAdd && typeof onAdd == 'function')) {
            console.error('未定义' + name + "-onadd方法");
            return;
        }
        _obj.name = name;
        if (onPrepare && typeof(onPrepare) == 'function') {
            onPrepare(_obj, that);
        }
        _obj.show = () => {
            if (!_obj.init || !_obj.lazy) {
                onAdd(_obj, that);
                _obj.init = true;
            }
            _obj.layer.show();
            _obj.layer.bringToFront();
            if (onShow && typeof onShow == 'function')
                onShow(_obj, that);
            _obj.visible = true;
            _obj.statusChange()
        }
        _obj.hide = () => {
            _obj.layer.hide();
            if (onHide && typeof onHide == 'function')
                onHide(_obj, that);
            _obj.visible = false;
            _obj.statusChange()
            if (that.popup)
                that.popup.checkClose(_obj.name)
        };
        _obj.remove = () => {
            that.map.remove(_obj.layer);
            if (onRemove && typeof onRemove == 'function')
                onRemove(_obj, that);
            delete that.topics[name]
            _obj = null;
        };
        if (onZoomend && typeof onZoomend == 'function') {
            that.map.on('zoomend', (ctx) => {
                onZoomend(_obj, that);
            });
        }
        that.topics[name] = _obj;
        return _obj;
    }


    //隐藏所有专题图
    hideTopics() {
        //this.popup.popClose();
        for (let key in this.topics) {
            this.topics[key].hide();
        }
    }

    getTopic(key) {
        return this.topics[key]
    }

    //切换图层显隐
    toggleTopic(name, flag) {
        if (!this.topics.hasOwnProperty(name)) {
            return {
                success: false,
                info: '未注册的图层：' + name
            };
        }
        if (flag) {
            this.TopicLayerHistory.push(name);
            this.topics[name].show()
        } else {
            this.topics[name].hide()
        }
        return {
            success: true,
            info: ''
        };
    }

    newVectorLayer(id) {
        return new maptalks.VectorLayer(id).addTo(this.map);
    }

}