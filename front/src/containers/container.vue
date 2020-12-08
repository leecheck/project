<template>
    <div class="p100 relative over-hidden  wrapper">
        <div class="full-bottom">
            <router-view ref="router"></router-view>
        </div>

        <Nav3su>
            <div slot="leftSide" class="rightWrapper">
                <div class="input-wp left" style="width: 285px;height: 30px;">
                    <el-input size="mini" placeholder="输入项目名称关键词检索..." v-model="input" clearable></el-input>
                </div>
                <div class="reverse menubtn font18 color-w left right" @click="search">搜索</div>
            </div>
            <div slot="centerSide" class="flare-wrapper abs-c">
                <flareTitle></flareTitle>
            </div>
            <div slot="rightSide" class="rightWrapper">
                <div @click="typeClick('CTD')" class="menubtn font18 color-w left" :class="typeSelected('CTD')">CTD</div>
                <div @click="typeClick('ADCP')" class="menubtn font18 color-w left" :class="typeSelected('ADCP')">ADCP</div>
                <div @click="typeClick('生物化学')" class="menubtn font18 color-w left" :class="typeSelected('生物化学')">生物化学</div>
                <div @click="typeClick('海洋气象')" class="menubtn font18 color-w left" :class="typeSelected('海洋气象')">海洋气象</div>
                <div class="user"><i class="el-icon-user-solid"></i></div>
            </div>
        </Nav3su>
    </div>
</template>

<script>
    import { Nav3su } from "typical-vue-comps"
    import flareTitle from "@/baseUI/flareTitle"


    export default {
        name: "container",
        components: {
            Nav3su,
            flareTitle
        },
        data() {
            return {
                input: "",
            }
        },
        methods: {
            typeSelected(key) {
                return this.$store.getters.type == key ? "type-onselect" : "";
            },

            typeClick(type) {
                this.$store.getters.type == type ? this.$store.dispatch('changeType', '') : this.$store.dispatch('changeType', type)
            },

            search() {
                if (this.input == '') {
                    this.$notify.warning("检索关键词为空")
                    return
                }
                this.$bus.emit('changeKeyword', this.input);
            }

        }
    }
</script>

<style lang="scss" scoped>
    $top-h:75px;

    $titlesize:2.3vw;

    .type-onselect {
        background-color: #106ab7 !important;
    }

    .wrapper /deep/ .wrapper {
        background: linear-gradient(to bottom, #012244, #062a50e0);
    }

    .flare-wrapper {
        width: 100%;
    }

    .flare-wrapper /deep/ .shiny {
        font-size: $titlesize;
        font-weight: 600;
    }

    .input-wp /deep/ .el-input__inner {
        background-color: rgba(255, 255, 255, 0);
        color: #ffffff;
        border-radius: 15px;
    }

    .full-bottom {
        position: absolute;
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
    }

    .navShadow {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 997;
        pointer-events: none;
    }

    .shadowLeft,
    .shadowRight {
        width: 290px;
        position: absolute;
        top: 0;
        left: 0;
    }

    .shadowRight {
        left: unset;
        right: 0;
    }

    .shadowCenter {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
    }

    .rightWrapper {
        margin: 6px 0 0 0;
        width: 100%;
        height: 26px
    }

    .reverse {
        transform: skew(30deg) !important;
    }

    .menubtn {
        transform: skew(-30deg);
        width: 70px;
        border: 1px solid;
        background-color: transparent;
        text-transform: uppercase;
        font-size: 14px;
        font-weight: 300;
        color: #82cae0;
        margin: 0 2px;
        line-height: 26px;
        height: 100%;
    }

    .menubtn:hover {
        background-color: #106ab7;
        -webkit-box-shadow: 10px 10px 99px 6px rgba(76, 201, 240, 1);
        -moz-box-shadow: 10px 10px 99px 6px rgba(76, 201, 240, 1);
        box-shadow: 10px 10px 99px 6px rgba(76, 201, 240, 1);
    }

    .user {
        font-size: 24px;
        float: right;
    }
</style>