<template>
    <ul id="map_mapTools" class="mapbtn-group">
        <li class=""> <a class="toolbar-btn" title="切换底图">
                <i class="iconfont icon-tubiao-" type="md-globe" /></a>
            <ul id="map_baseLayers" class="toolbar-sub-group">
                <li class=""><a @click="opt('layerChange','地图')" class="toolbar-sub-btn" title="">地图</a></li>
                <li class=""><a @click="opt('layerChange','遥感')" class="toolbar-sub-btn" title="">遥感</a></li>
                <li class=""><a @click="opt('layerChange','深色')" class="toolbar-sub-btn" title="">深色</a></li>
            </ul>
        </li>
        <li class=""> <a @click="opt('area')" class="toolbar-btn" :class="[areaActive ? 'active' : '','toolbar-btn']" title="面积量算"><i class="icon iconfont icon-edit-area"></i></a>
        </li>
        <li class=""> <a @click="opt('length')" class="toolbar-btn" :class="[lengthActive ? 'active' : '','toolbar-btn']" title="距离量算"><i class="icon iconfont icon-changdu"></i></a> </li>
        <li class=""> <a @click="opt('locate')" class="toolbar-btn" title="地图复位"><i class="iconfont icon-fuwei" type="md-home" /></a> </li>
        <li class=""> <a @click="opt('clear')" class="toolbar-btn" title="地图清屏"><i class="iconfont icon-qingping" type="ios-remove-circle-outline" /></a> </li>
    </ul>
</template>

<script>
    export default {
        name: 'mapOpt',
        components: {},
        props: {

        },
        mounted() {

        },
        data() {
            return {
                lengthActive: false,
                areaActive: false,
            }
        },
        methods: {
            opt(key, param) {
                switch (key) {
                    case 'area':
                        if (!this.areaActive) {
                            this.lengthActive = false;
                        }
                        this.areaActive = !this.areaActive;
                        this.$emit('opt', key, this.areaActive);
                        break;
                    case 'length':
                        if (!this.lengthActive) {
                            this.areaActive = false;
                        }
                        this.lengthActive = !this.lengthActive;
                        this.$emit('opt', key, this.lengthActive);
                        break;
                    case 'clear':
                        this.$emit('opt', key);
                        break;
                    case 'layerChange':
                        this.$emit('opt', key, param);
                        break;
                    case 'locate':
                        this.$emit('opt', key);
                        break;
                    default:
                        break;
                }
            },
            set(key, flag) {
                switch (key) {
                    case 'area':
                        this.areaActive = flag;
                        break;
                    case 'length':
                        this.lengthActive = flag;
                        break;
                    default:
                        break;
                }
            }
        },
        computed: {

        },
        watch: {

        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
    /*map组件toolsbar*/
    .active {
        background-color: #4b69ac !important;
    }

    .mapbtn-group {
        position: absolute;
        width: 33px;
    }

    .toolbar-btn i {
        color: white;
        font-size: 18px;
    }

    .toolbar-btn {
        background: #013273;
        border-radius: 3px;
        margin-bottom: 3px;
        display: block;
        width: 33px;
        height: 33px;
        text-align: center;
        line-height: 33px;
    }

    .toolbar-btn:hover {
        color: #fff;
        background: #1B6AD4;
        border-radius: 3px;
    }

    .mapbtn-group li ul {
        display: none;
    }

    .mapbtn-group li:hover ul {
        display: block;
    }

    .toolbar-btn:hover i {
        color: #fff;
    }

    .mapbtn-group .toolbar-sub-group {
        padding-right: 2px;
        position: absolute;
        right: 33px;
        margin-top: -34px;
        white-space: nowrap;
        height: 33px;
    }

    .toolbar-sub-group li {
        display: inline-block;
        overflow: hidden;
    }

    .toolbar-sub-btn {
        color: white !important;
        background-color: #04335c;
        margin-right: 1px;
        border-radius: 2px;
        display: block;
        height: 30px;
        width: 80px;
        text-align: center;
        line-height: 30px;
    }

    .toolbar-sub-btn:hover {
        color: #163b4e !important;
        background-color: #fff;
    }

    .i-c {
        font-size: 22px !important;
        vertical-align: -.225em;
    }

    .toolbar-sub-btn i {
        color: white;
        font-size: 18px !important;
    }
</style>