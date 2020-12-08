<template>
    <div class="relativeContainer p100">
        <div class="abs-vc ref-img">
            <div class="img-item imgnsfc"></div>
            <div class="img-item imgfio"></div>
        </div>
        <div id='map' class="p100"></div>
        <CruisePanel v-if="cruisePanelControl.show" ref="cruisePanel" @search="search" @segClick='segClick' @proDetail="proDetail"></CruisePanel>
        <ProInfo ref="proComp"></ProInfo>
        <LevelInfo ref="levelComp"></LevelInfo>
        <mapOpt class="map-opt" ref="opt" @opt='opt'></mapOpt>
    </div>
</template>

<script>
    import 'maptalks/dist/maptalks.css';
    import * as THREE from 'three';
    import * as maptalks from 'maptalks';
    import OneMap from "@/components/map/lib/OneMap"
    import { popData } from "./OneMapJS"
    import { MousePosConf, ScaleConf, OverviewConf, DistenceConf, AreaConf, ZoomConf } from "@/components/map/lib/MapStatic"

    import { symbols, base } from "./lib/mtkTool"
    import { eleConf } from "@/config/config"

    import * as CTD from "@/request/modules/CTD"
    import * as ADCP from "@/request/modules/ADCP"
    import * as Meteorology from "@/request/modules/Meteorology"

    import ProInfo from "@/components/map/ProInfo"
    import LevelInfo from "@/components/map/LevelInfo"

    import CruisePanel from "./CruisePanel"
    import mapOpt from './mapOpt'

    export default {
        components: {
            CruisePanel,
            ProInfo,
            LevelInfo,
            mapOpt
        },
        computed: {
            currentType() {
                return this.$store.getters.type;
            }
        },
        watch: {
            currentType() {
                this.segClick(null, false)
            }
        },
        name: "onemap",
        data() {
            return {
                cruisePanelControl: { show: true },
            };
        },
        mounted() {
            // init->getMapConf->addLayers
            let that = this;
            this.init();
            window.popupClick = function(type, prop) {
                let path = '';
                let query = {};
                let comp = that.$refs.levelComp;
                switch (type) {
                    case 'CTD可视化产品':
                    case '生物化学可视化产品':
                    case 'ADCP可视化产品':
                        comp.setData(eleConf[type], prop.list);
                        return;
                }
                that.$router.push({
                    path: path,
                    query: query
                });
            };

        },
        methods: {
            init: function() {
                let app = new OneMap('map', {
                    center: [122.266, 37.512],
                    zoom: 3,
                    maxZoom: 20,
                    pitch: 0,
                    zoomControl: ZoomConf,
                    overviewControl: OverviewConf,
                    mask: true,
                    scale: ScaleConf,
                    mousePos: MousePosConf,
                    topicStatusChange: function(topic) {

                    }
                })
                app.setBaseLayer("遥感");
                this.app = app;
                let map = this.map = app.map;
                let that = this;

                let topic = app.addBaseTopic({
                    name: "航段站位",
                    onPrepare(topic, app) {
                        topic.symbolNormal = symbols.ColorPoint({});
                        topic.symbolWithText = symbols.ColorPoint({
                            labelField: {
                                label: 'date'
                            }
                        });
                        topic.animSymbol = [{
                            "lineOpacity": 1,
                            'linePatternDx': 22,
                        }, {}];
                        topic.cruiseLine = [];
                        topic.draw = (data, seg, type, reView) => {
                            app.popup.popClose()
                            topic.cruiseLine = [];
                            if (!Array.isArray(data) || data.length == 0) {
                                that.$notify.warning("该航段此类站位数据为空");
                                return ;
                            }
                            Array.isArray(data) && data.forEach((item, idx) => {
                                let pt = [];
                                switch (type) {
                                    case '':
                                    case 'CTD':
                                    case '生物化学':
                                        pt = [item.stanceLong, item.stanceLat];
                                        item['date'] = item.arrStationTime;
                                        break;
                                    case 'ADCP':
                                    case '海洋气象':
                                        pt = [item.stationLon, item.stationLat]
                                        item['date'] = item.obsDate + " " + item.obsTime;
                                        break;
                                }
                                item['type'] = type;
                                let ps = idx % 5 == 0 || idx == (data.length - 1) ? topic.symbolWithText : topic.symbolNormal
                                let geo = new maptalks.Marker(pt, {
                                    properties: item,
                                    symbol: ps
                                }).addTo(topic.layer);
                                geo.on('click', (evt) => {
                                    geo.flash(100, 2, () => {
                                        popData(type, item).then((data) => {
                                            app.popup.popShow(pt, data);
                                        })
                                    })
                                });
                                topic.cruiseLine.push(pt)
                            })
                            let line = new maptalks.LineString(topic.cruiseLine, {
                                properties: {
                                    name: seg.cruiseSegmentName
                                },
                                symbol: symbols.slahLine({
                                    labelField: {
                                        label: 'name'
                                    }
                                })
                            }).addTo(topic.layer);
                            if (reView)
                                app.map.fitExtent(line.getExtent())
                            line.animate({
                                symbol: topic.animSymbol
                            }, {
                                duration: 2000,
                                repeat: true
                            });
                            topic.show()
                        }
                    },
                    onAdd(topic, app) {

                    },
                    onShow(topic, app) {

                    },
                    onHide(topic, app) {
                        topic.layer.clear();
                    }
                });
                topic.show()


                // let param = new FormData()
                // param.append("thin", 30)
                // param.append("cruiseId", 8)

                // Meteorology.spaceQuery(param).then((data) => {

                // })


            },

            //地图工具
            opt(key, param) {
                switch (key) {
                    case 'area':
                        if (param) {
                            this.app.distanceTool.disable();
                            this.app.distanceTool.clear();
                            this.app.areaTool.enable();
                        } else {
                            this.app.areaTool.disable();
                            this.app.areaTool.clear();
                        }
                        break;
                    case 'length':
                        if (param) {
                            this.app.areaTool.disable();
                            this.app.areaTool.clear();
                            this.app.distanceTool.enable();
                        } else {
                            this.app.distanceTool.disable();
                            this.app.distanceTool.clear();
                        }
                        break;
                    case 'layerChange':
                        this.app.setBaseLayer(param);
                        break;
                    case 'locate':
                        this.app.locate([122.266, 37.512], 3)
                        break;
                    case 'clear':
                        this.app.hideTopics();
                        break;
                    default:
                        break;
                }
            },
            search(param) {

            },
            segClick(seg, reView = true) {
                let that = this;
                this.currentSeg = seg || this.currentSeg;
                if (!this.currentSeg)
                    return
                let type = this.$store.getters.type;
                switch (type) {
                    case '':
                    case 'CTD':
                    case '生物化学':
                        CTD.queryByCruiseSeg(this.currentSeg.id).then((data) => this.draw(data, this.currentSeg, type, reView));
                        break;
                    case 'ADCP':
                        ADCP.queryByCruiseSeg(this.currentSeg.id).then((data) => this.draw(data, this.currentSeg, type, reView));
                        break;
                    case '海洋气象':
                        Meteorology.spaceQuery('', 30, this.currentSeg.id).then((data) => this.draw(data, this.currentSeg, type, reView));
                        break;

                }
            },
            draw(data, seg, type, reView) {
                let topic = this.app.getTopic("航段站位");
                topic.layer.clear();
                topic.draw(data, seg, type, reView)
            },
            proDetail(cruise) {
                this.$refs.proComp.setData(cruise)
            }
        },
        destroyed() {

        },
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
    .map-opt {
        right: 10px;
        opacity: 1;
        transition-duration: 1.5s;
        position: absolute;
        bottom: 60px;
    }

    /* 弹出面板css */
    .popup-btn {
        float: right;
        height: 24px;
        line-height: 24px;
        background: #061867c7;
        margin: 4px 8px 4px 4px;
        text-align: center;
        font-family: 'PingFang SC';
        text-decoration: none;
        font-size: 14px;
        color: white;
        transition: 0.5s;
        box-shadow: 2px 1px 0 #20e0ff78;
        transform: skewX(-15deg);
    }

    .popup-label-btn {
        float: right;
        background: #061867c7;
        text-align: center;
        font-family: 'PingFang SC';
        text-decoration: none;
        font-size: 14px;
        color: white;
        transition: 0.5s;
        box-shadow: 2px 1px 0 #20e0ff78;
        transform: skewX(-15deg);
    }

    .circle-text {
        font-size: 14px;
        font-family: 'PingFang SC';
        color: #f3f3f3;
    }

    .main-label {
        position: absolute;
        left: 0;
        top: 0px;
        height: 40px;
        width: 100%;
        background: #0378922e;
        font-size: 20px;
        line-height: 40px;
    }

    .main-label-line {
        position: absolute;
        left: 80px;
        top: 40px;
        height: 100px;
        width: 1px;
        border-left: 1px #16c8fd solid;
    }

    .show-text {
        text-align: center;
        line-height: 25px;
        height: 25px;
        font-size: 22px;
        font-weight: 900;
        color: #20e0ff;
    }

    .label-text {
        font-family: 'PingFang SC';
        text-align: center;
        line-height: 25px;
        height: 25px;
        font-size: 13px;
        color: white;
    }

    .pop-close {
        color: #fff;
    }

    .popup-label-line {
        width: 96%;
        margin: 2px 2%;
        float: left;
        padding: 4px 2px;
    }

    .bootstrap {
        background-color: #1677ff1f;
        border-left: 2px solid #00daf8;
    }

    .popup-label {
        text-align: right;
        float: left;
        font-size: 14px;
        font-family: 'PingFang SC';
        border-left: 2px solid #00daf800;
        font-weight: bold;
        width: 70px;
        color: #55b3d0;
    }

    .popup-btns-label {
        text-align: left;
        float: left;
        font-size: 14px;
        font-family: 'PingFang SC';
        border-left: 2px solid #00daf800;
        font-weight: bold;
        width: 200px;
        color: white;
    }

    .popup-data {
        text-align: left;
        font-family: 'PingFang SC';
        width: 196px;
        font-size: 14px;
        float: right;
        color: white;
    }

    .mtk-scale {
        border-right: 1px solid rgb(255, 255, 255);
        border-bottom: 1px solid rgb(255, 255, 255);
        border-left: 1px solid rgb(255, 255, 255);
        border-image: initial;
        border-top: none;
        line-height: 1.1;
        padding: 0px;
        color: rgb(255, 255, 255);
        font-size: 11px;
        text-align: center;
        white-space: nowrap;
        overflow: hidden;
        box-sizing: content-box;
        background: rgba(255, 255, 255, 0);
    }

    .mtk-overview-btn {
        cursor: pointer;
        background: #fff;
        width: 23px;
        height: 23px;
        position: absolute;
        bottom: 1px;
        right: 1px;
        font: 18px sans-serif;
        text-align: center;
        line-height: 23px;
        color: #363539;
    }

    .ref-img {
        width: 590;
        bottom: 10px;
        height: 70px;
        z-index: 2;
    }

    .img-item {
        float: left;
        height: 64px;
        margin-right: 10px;
        opacity: 0.7;
    }

    .imgfio {
        width: 200px;
        background: url('/static/img/fio.png') no-repeat;
        background-size: 100% 100%;
    }

    .imgnsfc {
        width: 360px;
        background: url("/static/img/nsfc.png") no-repeat;
        background-size: 100% 100%;
    }
</style>