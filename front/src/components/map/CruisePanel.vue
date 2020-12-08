<template>
    <div :class="floding" class="cruise-panel">
        <SqurePanel>
            <div class="panel-title">
                <div class="title">共享航次/数据检索</div>
            </div>
            <div class="line title-line"></div>
            <div class="panel-func" @click="panelFold">
                <i v-if="status=='expande'" class="el-icon-arrow-up"></i>
                <i v-if="status=='fold'" class="el-icon-arrow-down"></i>
            </div>
            <div v-if="status == 'expande'" class="params">
                <el-form ref="form" label-width="80px">
                    <el-form-item label="项目年度">
                        <el-date-picker type="year" size="mini" value-format="yyyy" placeholder="选择年度" :default-time="['00:00:00', '23:59:59']" :picker-options="pickerOptions" v-model="paramFrom.year">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="项目名称">
                        <el-input placeholder="请输入项目名称" size="mini" v-model="paramFrom.proName">
                            <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                        </el-input>
                    </el-form-item>
                    <div class="w100 left margin2-0">
                        <div @click="typeClick('CTD')" :class="typeSelected('CTD')" class="type left">
                            <svg class="icon type-icon" aria-hidden="true">
                                <use xlink:href="#icon-yayan"></use>
                            </svg>
                            <span class="type-text">CTD</span>
                        </div>
                        <div @click="typeClick('ADCP')" :class="typeSelected('ADCP')" class="type left">
                            <svg class="icon type-icon" aria-hidden="true">
                                <use xlink:href="#icon-liusuyi"></use>
                            </svg>
                            <span class="type-text">ADCP</span>
                        </div>
                        <div @click="typeClick('生物化学')" :class="typeSelected('生物化学')" class="type left">
                            <svg class="icon type-icon" aria-hidden="true">
                                <use xlink:href="#icon-jianyan-shenghualei"></use>
                            </svg>
                            <span class="type-text">生物化学</span>
                        </div>
                        <div @click="typeClick('海洋气象')" :class="typeSelected('海洋气象')" class="type left">
                            <svg class="icon type-icon" aria-hidden="true">
                                <use xlink:href="#icon-qixiang"></use>
                            </svg>
                            <span class="type-text">海洋气象</span>
                        </div>
                    </div>
                    <div class="line left w100"></div>
                </el-form>
            </div>
            <div v-if="status == 'expande'" class="results">
                <div v-for="(cruise,index) in cruises" v-bind:key="index" class="cruise-list left relative">
                    <el-tooltip class="cruise-tooltip" effect="dark" :content="cruise.cruiseProjectInfo.projectName" placement="top-start">
                        <div class="left project-name ellipse">{{cruise.cruiseProjectInfo.projectName}}</div>
                    </el-tooltip>
                    <div class="detail-btn" @click="proDetail(cruise)">详细</div>
                    <div class="left cruise-info">{{cruise.basicCruiseInfo.cruiseName}}。{{cruise.cruiseProjectInfo.obsInfo}}</div>
                    <div style="clear:both"></div>
                    <div class="cruise-segs">
                        <div class="seg" @click="segClick(seg)" v-bind:key="seg.id" v-for="(seg,index) in cruise.cruiseSegmentInfos">
                            <div class="left seg-index">{{index + 1}}</div>
                            <el-tooltip class="cruise-tooltip" effect="dark" :content="seg.cruiseSegmentName" placement="top-start">
                                <div class="left seg-name ellipse">{{seg.cruiseSegmentName}}</div>
                            </el-tooltip>
                            <div class="left seg-time">{{seg.startDate}}</div>
                        </div>
                    </div>
                </div>
            </div>
        </SqurePanel>
    </div>

</template>

<script>
    import SqurePanel from "@/baseUI/SqurePanel"


    import * as Cruise from "@/request/modules/Cruise"

    export default {
        name: "CruisePanel",
        components: {
            SqurePanel
        },
        props: {},
        computed: {
            floding() {
                if (this.status == 'expande') {
                    return "";
                } else {
                    return 'fold'
                }
            },
            currentKeyword() {
                return this.$store.getters.keyword;
            }
        },
        watch: {
            // currentKeyword(word) {
            //     this.searchKeyword(word)
            // }
        },
        data() {
            return {
                status: "fold",
                paramFrom: {
                    year: "2016",
                    proName: "",
                },
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                },
                activeType: "",
                cruises: [],
                cruisesTest: [{
                    cruiseProjectInfo: {
                        projectName: "台湾海峡科学考察实验研究 (2016年)",
                        obsInfo: "项目依托“延平2号”科学考察船，开展了物理海洋、海洋生物、地球化学、海洋地质专业调查，完成了4套锚系浅标系统的布放，2套漂浮式沉积物捕获器的布放与回收，149个大面站的物理海洋学、生物地球化学、生物拖网与沉积物采集任务，2个72小时连续站的水文学、生态学及生物地球化学的水团跟踪观测。"
                    },
                    basicCruiseInfo: {
                        cruiseName: "2016年度台湾海峡航次",
                    },
                    invgionAreaId: "台湾海峡西部各河口、厦门湾、台湾海峡浅滩及南海东北部部分陆架等海区",
                    qizhi: "2016-5 至 2016-8",
                    cruiseSegmentInfos: [{
                        cruiseSegmentName: "2016年度台湾海峡航次春季航段",
                        startDate: "2016-03-28",
                        endDate: "2016-04-04"
                    }, {
                        cruiseSegmentName: "2016年度台湾海峡航次春季航段",
                        startDate: "2016-03-28",
                        endDate: "2016-04-04"
                    }]
                }]
            }
        },
        mounted() {
            this.$bus.on('changeKeyword', this.searchKeyword);
        },
        beforeDestroy() {
            this.$bus.off('changeKeyword', this.searchKeyword);
        },
        methods: {

            typeSelected(key) {
                return this.$store.getters.type == key ? "type-onselect" : "";
            },
            panelFold() {
                if (this.status == 'expande') {
                    this.status = 'fold';
                } else {
                    this.status = 'expande'
                }
            },
            searchKeyword(word) {
                this.paramFrom.year = "";
                this.paramFrom.proName = word;
                this.search()
            },
            search() {
                let that = this;
                if (this.paramFrom.year == '' && this.paramFrom.proName == '') {
                    this.$notify.warning("检索条件为空")
                    return
                }
                this.$emit("search", this.paramFrom)
                Cruise.queryLike(this.paramFrom.year, this.paramFrom.proName).then((cruiseList) => {
                    that.cruises = cruiseList;
                })
                this.status = 'expande'
            },
            segClick(seg) {
                this.$emit('segClick', seg);

            },
            typeClick(type) {
                this.$store.getters.type == type ? this.$store.dispatch('changeType', '') : this.$store.dispatch('changeType', type)
            },
            proDetail(cruise) {
                this.$emit('proDetail', cruise)
            }
        }
    }
</script>

<style lang="scss" scoped>
    $panel-top : 10px;

    .detail-btn {
        position: absolute;
        right: 4px;
        top: 10px;
        cursor: pointer;
        color: #b0c4de;
        width: 47px;
        background: rgba(22, 119, 255, 1);
        border-left: 2px solid cyan;
        border-top: none;
        border-bottom: none;
        border-right: none;
        display: none
    }

    .project-name {
        width: 100%;
        text-align: left;
        background: rgba(22, 119, 255, 0.2);
        border-left: 3px solid #f9d713;
        overflow: hidden;
        font-size: 18px;
        height: 30px;
        line-height: 30px;
        text-overflow: ellipsis;
        white-space: nowrap;
        box-sizing: border-box;
        padding: 0 5px;
    }

    .cruise-list {
        width: 100%;
        color: #f1f3f6;
        background: rgba(47, 95, 123, .34);
        padding: 4px;
        padding: 5px;
        border-radius: 2px;
        box-sizing: border-box;
        margin: 2px 0;
    }

    .cruise-list:hover {
        box-shadow: 2px 3px 3px -2px rgba(135, 206, 235, .3);
    }

    .cruise-list:hover .detail-btn {
        display: block
    }

    .type {
        position: relative;
        width: 24%;
        height: 60px;
        box-sizing: border-box;
        margin: 0 0 0 1%;
        transition: 0.5s;
        border-radius: 5px;
    }

    .type-onselect {
        background: #62d4f044
    }

    .type::after {
        position: absolute;
        top: 5px;
        right: -2px;
        width: 1px;
        height: 50px;
        border-right: 1px solid #69e0fb46;
        content: "";
    }

    .last::after {
        border: none
    }

    .type-text {
        height: 18px;
        line-height: 18px;
        display: block;
        margin-top: 7px;
        font-size: 13px;
    }

    .type:hover {
        background: #62d4f044
    }

    .type-icon {
        margin: 0 auto;
        display: block;
        font-size: 28px;
    }

    .title {
        position: absolute;
        color: white;
        font-weight: 600;
        font-size: 18px;
        top: 8px;
        left: 22px;
        text-align: left;
        background: linear-gradient(#dce7ff, #8da8da);
        -webkit-background-clip: text;
        color: transparent;
    }

    .fold {
        height: 45px;
    }

    .cruise-panel {
        position: absolute;
        left: 20px;
        top: 95px;
        bottom: 30px;
        width: 350px;
    }

    .cruise-info {
        text-align: left;
        text-indent: 2rem;
        font-size: 12px;
        padding: 2px 0;
        margin: 2px 0;
    }

    .panel-func {
        color: #ffffff;
        top: 7px;
        width: 20px;
        height: 20px;
        position: absolute;
        right: 30px;
        font-size: 24px;
    }

    .line {
        border-bottom: 1px solid #62d3f0
    }

    .title-line {
        position: absolute;
        top: $panel-top + 30;
        left: 5px;
        right: 5px;
    }

    .params {
        position: absolute;
        top: 50px;
        height: 300px;
        width: 340px;
    }

    .results {
        position: absolute;
        top: 200px;
        bottom: 8px;
        width: 340px;
        overflow-y: auto;
        padding: 4px;
        box-sizing: border-box;
    }

    .params /deep/ .el-form-item {
        margin-bottom: 2px;
    }

    .params /deep/ .el-date-editor.el-input,
    .el-date-editor.el-input__inner {
        width: 100%;
    }

    .params /deep/ .el-input__inner {
        background-color: $themeColor;
        color: rgb(255, 255, 255);
    }

    .params /deep/ .el-form-item__label {
        color: $fontColor
    }

    .params /deep/ .el-input-group {
        vertical-align: middle
    }

    .cruise-segs {
        width: 100%;
        font-size: 12px;
        margin: 2px 0;
        float: left;
    }

    .seg:hover {
        background: #204c75;
    }

    .seg {
        width: 100%;
        float: left;
        height: 17px;
        line-height: 17px;
        margin: 2px 0;
        background: #204c75a6;
        border-radius: 2px;
        cursor: pointer;
    }

    .seg-time {
        width: 75px;
        text-align: right;
    }

    .seg-name {
        width: 210px;
        margin-left: 5px;
        text-align: right;
    }

    .seg-index {
        width: 18px;
        text-align: right;
        border-left: 2px solid springgreen;
    }
</style>