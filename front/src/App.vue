<template>
    <div id="app" ref="app">
        <router-view v-if="isRouterAlive" />
    </div>
</template>

<script>
    import "typical-base/css/reset.css";
    import "typical-base/css/base.css";
    import 'nprogress/nprogress.css'
    import Vue from "vue";
    export default {
        name: "App",

        provide() {
            return {
                reload: this.reload
            };
        },
        components: {},
        data() {
            return {
                isRouterAlive: true
            };
        },
        methods: {
            reload() {
                //初始化
                this.isRouterAlive = false;
                this.$nextTick(() => (this.isRouterAlive = true));
            }
        },
        mounted() {}
    };
</script>

<style lang="scss">
    html {
        width: 100%;
        height: 100%;
    }

    body {
        width: 100%;
        height: 100%;
    }

    ul,
    ol,
    li {
        list-style: none;
    }

    #app {
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        background: $themeColor url(../static/img/background.png) no-repeat;
        background-size: 100% 100%;
        color: $fontColor;
        position: relative;
        width: 100%;
        height: 100%;
        float: left;
        overflow: hidden;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    }

    .margin2-0 {
        margin: 2px 0;
    }

    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }

    .maptalks-zoom-slider-reading {
        background: $themeColor !important;
    }

    .maptalks-overview-button {
        width: 22px !important;
        height: 22px !important;
        bottom: 0px !important;
        right: 0px !important;
        line-height: 22px !important;
    }
</style>
