<template>
    <div v-if="show" class="absoluteContainer detail">
        <div class="title">
            {{data.title}}
        </div>
        <ul class="monitoring-switch">
            <li v-for="item in data.list" v-bind:class="activeLayer(item)" @click="layerChange(item)">{{ item.层次 }}</li>
        </ul>
        <div class="content" style="position: absolute;left: 120px;top:50px;bottom: 10px;right: 10px;scroll:auto">
            <div v-for="(value,key) in point" class=" contentitem" style="width: 50%;height: 30px;float: left;;">
                <div class="popup-label">{{key}}:</div>
                <div class="popup-data ellipse">{{value}}</div>
            </div>
        </div>
        <div class="pop-close" @click="close" style="position:absolute;right:12px;top:15px;cursor: pointer;">✖</div>
    </div>
</template>

<script>
    import videojs from 'video.js'
    import 'videojs-contrib-hls'
    import { video1Conf } from "@/util/mapConf"
    import popbg from "@/components/map/pic/popbg.png"
    export default {
        name: "detail",
        components: {

        },
        data() {
            return {
                show: false,
                data: {
                    title: "监测点：",
                    list: [{
                        "点位名称": "SDH11013",
                        "层次": "表",
                    }, {
                        "点位名称": "SDH11013",
                        "层次": "底",

                    }, {
                        "点位名称": "SDH11013",
                        "层次": "中",

                    }]
                },
                point: {
                    "点位名称": "SDH11013",
                    "层次": "表",
                },
                active: "表",
            }
        },
        methods: {
            layerChange(item) {
                this.point = item
                this.active = item['层次'];
            },
            activeLayer(item) {
                return this.active == item['层次'] ? 'active' : '';
            },
            setData(data) {
                this.show = true
                this.data = data;
                this.point = data.list[0],
                this.active = this.point['层次']
            },
            close() {
                this.show = false
            }
        },
        mounted() {

        },
        destroyed() {

        }
    }
</script>

<style lang="scss" scoped>
    .detail {
        right: 300px;
        top: 200px;
        bottom: 200px;
        width: 600px;
        z-index: 99;
        background: url('/static/img/detailbg.png');
        background-size: 100% 100%;
    }

    .title {
        font-family: 'PingFang SC';
        margin: 8px 4px 4px 4px;
        height: 34px;
        font-size: 20px;
        line-height: 34px;
        background: linear-gradient(to right, #1677FF00, #1677FC, #1677FF00)
    }

    .bootstrap {
        background-color: #122e5a;
        border-left: 2px solid #00daf8;
    }

    .popup-label {
        text-align: right;
        float: left;
        font-size: 16px;
        font-family: 'PingFang SC';
        border-left: 2px solid #00daf800;
        width: 86px;
    }

    .popup-data {
        text-align: left;
        font-family: 'PingFang SC';
        width: 130px;
        font-size: 16px;
        float: right
    }

    .monitoring-switch {
        position: relative;
        float: left;
        width: 100px;


        >li {
            background: rgba(14, 39, 126, 1);
            color: rgba(22, 119, 255, 1);
            cursor: pointer;
            display: inline-block;
            font-size: 18px;
            font-family: 'HYK2GJM_0', 'FZGONGYHJW--GB1-0';
            font-weight: 400;
            height: 30px;
            letter-spacing: 1px;
            line-height: 30px;
            margin-top: 10px;
            text-shadow: 1px 2px 2px rgba(0, 30, 73, 1);
            width: 80px;
        }

        >li.active {
            background: linear-gradient(90deg,
                    rgba(2, 85, 255, 1),
                    rgba(22, 119, 255, 1));
            color: rgba(255, 255, 255, 1);
        }
    }
</style>