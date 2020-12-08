<@l.simple title="首页" import="base,vue,iview,layout,leaflet"
js="js/lib.js,js/leaflet/leaflet-heat.js,js/leaflet/MiniMap.js"
css="js/leaflet/MiniMap.css">

<div id="index">
    <h1>Hello boy,
        <#assign  user = env.getCurrentUser()/>
        <#if user??>
            ${user.username!}
        </#if>
        <a href="<@c.rootPath/>/admin/sysUser/list">用户管理</a></h1><br>
    <h1><i class="ivu-icon ivu-icon-ios-appstore"></i><a href="<@c.rootPath/>/logout">注销</a></h1><br>
    
    <br>
    <div class="slide" style="width: 300px;">
        <Slider v-model="slide" :min="92.5" :max="174.3" range></Slider>
    </div>
    <div>
        <i-select v-model="select" style="width:200px">
            <i-option v-for="item in syncLayerGroup" :value="item.value" :key="item.value">{{ item.label }}</i-option>
        </i-select>
    </div>
    <br>
    <br>
    <br>
    <br>
    <h1><a @click="notice">提示信息</a></h1><br>
    <h1><a @click="grpctest">grpc test</a></h1><br>
    <h1><a @click="grpcFlow">grpcFlow</a></h1><br>
    <h1><a @click="requestNodes">requestNodes</a></h1><br>
    <h1><a @click="getResult">getResult</a></h1><br>
    <div id="map" style="width: 100%;height: 800px;"></div>
</div>

<style type="text/css">

</style>

<script type="text/javascript">
    var indexData = {
        slide:[110, 130],
        select:"",
        syncLayerGroup:[
            {
                value:"baseLayers",
                label:"基础底图"
            },
            {
                value:"topicLayers",
                label:"专题图"
            }
        ],
    };
    var method = {};
    method.initMap = function () {
        var normalm = L.tileLayer.chinaProvider('TianDiTu.Normal.Map', {
                    maxZoom: 18,
                    minZoom: 1
                }),
                normala = L.tileLayer.chinaProvider('TianDiTu.Normal.Annotion', {
                    maxZoom: 18,
                    minZoom: 1
                }),
                imgm = L.tileLayer.chinaProvider('TianDiTu.Satellite.Map', {
                    maxZoom: 18,
                    minZoom: 1
                }),
                imga = L.tileLayer.chinaProvider('TianDiTu.Satellite.Annotion', {
                    maxZoom: 18,
                    minZoom: 1
                });
        var miniMapLayer = L.tileLayer.chinaProvider('TianDiTu.Normal.Map', {
            maxZoom: 10,
            minZoom: 1
        });
        var normal = L.layerGroup([normalm, normala]),
                image = L.layerGroup([imgm, imga]);
        var baseLayers = {
            "地图": normal,
            "影像": image,
        };
        var map = L.map("map", {
            center: [31.552, 120.20],
            zoom: 9,
            layers: [image],
            zoomControl: false,
            attributionControl: false,
        });
        var data = [
            {
                coor:[31.552, 120.20],
                attr:{
                    id:1
                }
            },
            {
                coor:[31.554, 120.20],
                attr:{
                    id:2
                }
            }
        ];
        for (var i = 0;i<data.length;i++){
            var datai = data[i];
            var marker = L.marker(datai.coor,datai.attr).addTo(map);
            marker.on('click', function(e) {
                var s = e.target;
                var popup = L.popup()
                        .setLatLng([e.latlng.lat, e.latlng.lng])
                        .setContent("内容")
                        .openOn(map);
            });
        }
        var heat = L.heatLayer([[31.512, 120.30],[31.562, 120.40],[31.542, 120.26],[31.542, 120.40],[31.522, 120.70],
                [31.552, 120.20],[31.559, 120.24],[31.452, 120.70],[31.532, 120.210],[31.582, 120.60]])
            .addTo(map);
        var miniMap = new L.Control.MiniMap(miniMapLayer, { toggleDisplay: true }).addTo(map);
    };
    var indexApp= new Vue({
        el: '#index',
        data: indexData,
        mounted:function () {
            method.initMap();
        },
        methods: {
            notice: function () {
                this.$Notice.info({
                    title: '提示',
                    desc: '验证iview消息提示相关功能，框架开发中 ',
                    duration: 0
                });
            },
            grpctest:function () {
                _$.ajax({
                    url: '<@c.rootPath/>' + "/index/grpc",
                    dataType: 'json',
                    type: 'POST',
                    data: {},
                    success: function (_r, _s) {
                        if (_r.success) {
                            _u.success(indexApp,'请求成功');
                        } else {
                            _u.error(indexApp,_r.message);
                        }
                    },
                    fail: function (_ex) {
                        _u.error(indexApp,JSON.stringify(_ex));
                    }
                });
            },
            grpcFlow:function () {
                _$.ajax({
                    url: '<@c.rootPath/>' + "/index/grpcFlow",
                    dataType: 'json',
                    type: 'POST',
                    data: {},
                    success: function (_r, _s) {
                        if (_r.success) {
                            _u.success(indexApp,'请求成功');
                        } else {
                            _u.error(indexApp,_r.message);
                        }
                    },
                    fail: function (_ex) {
                        _u.error(indexApp,JSON.stringify(_ex));
                    }
                });
            },
            requestNodes:function () {
                _$.ajax({
                    url: '<@c.rootPath/>' + "/index/requestNodes",
                    dataType: 'json',
                    type: 'POST',
                    data: {},
                    success: function (_r, _s) {
                        if (_r.success) {
                            _u.success(indexApp,'请求成功');
                        } else {
                            _u.error(indexApp,_r.message);
                        }
                    },
                    fail: function (_ex) {
                        _u.error(indexApp,JSON.stringify(_ex));
                    }
                });
            },
            getResult:function () {
                _$.ajax({
                    url: '<@c.rootPath/>' + "/index/getResult",
                    dataType: 'json',
                    type: 'POST',
                    data: {},
                    success: function (_r, _s) {
                        if (_r.success) {
                            _u.success(indexApp,'请求成功');
                        } else {
                            _u.error(indexApp,_r.message);
                        }
                    },
                    fail: function (_ex) {
                        _u.error(indexApp,JSON.stringify(_ex));
                    }
                });
            }
        }
    });
</script>
</@l.simple>
