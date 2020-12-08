<template>
    <transition name="el-zoom-in-top">
        <div v-if="visible" class="abs-vc wrapper">
            <SqurePanel>
                <div class="close" @click="close">×</div>
                <div class="pannel-warpper">
                    <div class="part left">
                        <div class="panel-title left">
                            <svg class="icon proinfo-icon" aria-hidden="true">
                                <use xlink:href="#icon-xiangmu"></use>
                            </svg>
                            <div class="title">{{cruiseProjectInfo.projectName}}</div>
                        </div>
                        <div class="info-part left">
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">项目批准号</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.projectCode | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">项目来源</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.projectSource | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">项目类型</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.projectType | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">项目经费</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.projectFund | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">起始时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.startTime | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">终止时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{cruiseProjectInfo.endTime | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查海域</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{cruiseProjectInfo.invgionAreaId | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查内容</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{cruiseProjectInfo.obsInfo | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">科学目标</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{cruiseProjectInfo.scienObjs | fn}}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                    <div class="line title-line"></div>
                    <div class="part left">
                        <div class="panel-title left">
                            <svg class="icon proinfo-icon" aria-hidden="true">
                                <use xlink:href="#icon-hangci"></use>
                            </svg>
                            <div class="title">{{basicCruiseInfo.cruiseName}}</div>
                        </div>
                        <div class="info-part left">
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">航次编号</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.cruiseDentifr | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">起始时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.startTime | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">结束时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.endTime | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查季节</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.startTime | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">调查总天数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.invgionSumDays | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">总航程(海里)</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.totalVoyage | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">搭载项目数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.chiefId | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">参与人次</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{basicCruiseInfo.partPersonNumber | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查海域</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{basicCruiseInfo.invgionAreaId | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">航次经纬四至</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">东 {{basicCruiseInfo.eastLong | fn}} 西 {{basicCruiseInfo.westLong | fn}} 南 {{basicCruiseInfo.southLat | fn}} 北 {{basicCruiseInfo.northLat | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">航次备注</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{basicCruiseInfo.cruiseRemark | fn}}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                    <div class="line title-line"></div>
                    <div class="part left" v-for="seg in cruiseSegmentInfos" v-bind:key="seg.id">
                        <div class="panel-title left">
                            <svg class="icon proinfo-icon" aria-hidden="true">
                                <use xlink:href="#icon-hangduan"></use>
                            </svg>
                            <div class="title">{{seg.cruiseSegmentName}}</div>
                        </div>
                        <div class="info-part left">
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">站位数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.standNumber | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">测线数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.mearLineNumber | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">测线长度</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.mearLineLength | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">断面数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.obsSectNumber | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">航程(海里)</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.cruiseVoyage | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">参与单位数量</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.partUntisNumber | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">搭载项目数</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.caryItemNumber | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">起始时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.startDate | fn}}</div>
                                </el-col>
                                <el-col :span="3">
                                    <div class="grid-key">终止时间</div>
                                </el-col>
                                <el-col :span="5">
                                    <div class="grid-value">{{seg.endDate | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查海域</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{seg.invgionAreaId | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">调查内容</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{seg.obsInfo | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">航段经纬四至</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">东 {{basicCruiseInfo.eastLong | fn}} 西 {{basicCruiseInfo.westLong | fn}} 南 {{basicCruiseInfo.southLat | fn}} 北 {{basicCruiseInfo.northLat | fn}}</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-col :span="3">
                                    <div class="grid-key">备注</div>
                                </el-col>
                                <el-col :span="21">
                                    <div class="grid-value">{{seg.cruiseSegmentRemark | fn}}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </div>

            </SqurePanel>
        </div>
    </transition>

</template>

<script>
    import SqurePanel from "@/baseUI/SqurePanel"

    export default {
        name: "proinfo",
        components: {
            SqurePanel
        },
        props: {},
        data() {
            return {
                visible: false,
                "basicCruiseInfo": {
                    "caryItemNumber": "",
                    "chiefExecutiveScientistId": "",
                    "chiefId": 34,
                    "cruiseDentifr": "NORC2015-05",
                    "cruiseName": "2016年度吕宋海峡—南海海盆航次",
                    "cruiseRemark": "主要开展地质参试项目的站位、大面站、连续站作业站位及走航观测工作",
                    "cruiseReportId": "1040,38,1630",
                    "dataChangeTime": null,
                    "dataContactId": null,
                    "dataEditId": 1,
                    "dataFileId": "",
                    "dataReportId": "",
                    "eastLong": "119°",
                    "editTime": "2020-05-22 16:28:48",
                    "endTime": "2016-06-07",
                    "examProgressId": 4,
                    "execOrgId": null,
                    "flighNumber": 2,
                    "id": 9,
                    "imagesInfoId": null,
                    "invgionAreaId": "南海东北部、吕宋海峡海域",
                    "invgionExecTime": "",
                    "invgionSeason": "春季",
                    "invgionSumDays": "38",
                    "isDisplay": 0,
                    "northLat": "23°",
                    "obsSectNumber": null,
                    "partDictPersonNumber": 83,
                    "partPersonNumber": 84,
                    "partUntisNumber": null,
                    "projectInfoId": 45,
                    "projectLeadId": null,
                    "retaProgressId": null,
                    "southLat": "17°",
                    "spareValue1": "",
                    "spareValue2": "",
                    "spareValue3": "",
                    "standNumber": 75,
                    "startTime": "2016-05-01",
                    "totalVoyage": "3745.7",
                    "westLong": "110°"
                },
                "cruiseProjectInfo": {
                    "chiefExecutiveScientistId": "246",
                    "chiefScientistId": "246",
                    "editTime": "2020-03-27 10:40:21",
                    "endTime": "2016-12-31",
                    "examProgressId": 4,
                    "id": 45,
                    "implYear": "2016",
                    "investEndTime": null,
                    "investStartTime": "2016-05-01 至 2016-05-14 ；2016-05-15 至 2016-06-22",
                    "invgionAreaId": "南海北部及吕宋海峡附近",
                    "isDisplay": 0,
                    "obsInfo": "1) 物理海洋学科：CTD观测、LADCP 流速剖面、走航ADCP 流速剖面、海洋湍流剖面观测。2) 海洋气象学科：常规气象观测。3) 海洋地质学科：大体积采水、大体积过滤、多波束地形地貌测量、沉积物捕获、柱状取样、箱式采泥、重力活塞取样、悬浮物体浓度测量、拖网采集海底表层样品。4) 海洋化学学科：常规采水、大体积采水、走航观测CO2、DIC、pH 观测。5) 环境生物学科：常规采水、大体积采水、生物拖网、水体表观光学和固有光学观测。",
                    "projectCode": "41449905",
                    "projectCompanysId": 9,
                    "projectEditId": 2,
                    "projectFund": 600,
                    "projectLeadId": 1025,
                    "projectName": "吕宋海峡-南海海盆科学考察实验研究",
                    "projectRemark": "（因故推迟至2016年执行）",
                    "projectSource": "国家自然科学基金",
                    "projectType": "应急管理项目",
                    "retaChangeTime": "2017-04-15",
                    "retaProgressId": 6,
                    "scienObjs": "本航次围绕“南海深海过程演变”重大研究计划的科学目标及其相关海洋环境科学问题，了解和探索南海陆架和深水区域海洋动力与生物地球化学过程，南海东北部深层环流和沉积搬运过程，利用“东方红2”号调查船在南海北部及吕宋海峡附近选择重点海区，进行多学科样品采集、断面调查，获取海洋动力、沉积、化学、生物等多学科综合观测资料，为我国南海沉积、物理-生物地球化学耦合研究提供了丰富素材，有望进一步了解南海的动力、环境与生态过程。",
                    "startTime": "2015-01-01",
                    "submitStatus": "",
                    "submitTime": "",
                    "surshipId": ""
                },
                "cruiseSegmentInfos": [{
                        "caryItemNumber": 8,
                        "cruiseId": 9,
                        "cruiseSegmentName": "2016年度吕宋海峡—南海海盆综合航次第一航段",
                        "cruiseSegmentRemark": "",
                        "cruiseVoyage": "1664.5",
                        "dataChangeTime": null,
                        "dataContactId": "",
                        "dataEditId": 2,
                        "eastLong": "119°",
                        "editTime": "2020-05-14 17:23:28",
                        "endDate": "2016-05-14",
                        "execOrgId": null,
                        "id": 9,
                        "imagesInfoId": "147",
                        "impChiefId": "246",
                        "invgionAreaId": "南海北部及吕宋海峡",
                        "isDisplay": 0,
                        "mearLineLength": null,
                        "mearLineNumber": null,
                        "northLat": "23°",
                        "obsInfo": "第一航段计划在9个站位开展CTD采水、箱式取样、重力取样及DCPT测试工作，实际完成11个站位（其中预定站位9个，新增站位2个）和7个泥火山站位的调查工作。共计完成了16个站位的CTD剖面测量及不同深度的海水采取、17个站位共42组沉积物箱式取样、6个站位共23组沉积物重力取样、3个站位的沉积物DCPT测试、以及大气悬浮颗粒物和表层海水的走航取样。其中，CTD采水和箱式取样的站位和取样量都远远超过预定计划；预设站位中有4个站位（C2、C1、S7、G1）位于陆坡部位，箱式取样空采，或仅获取少量碳酸盐硬质结核，因而未进行重力取样和DCPT测试；另有1个站位（G2）重力取样量非常少，未进行DCPT测试；增加2个站位（S6、D3），在S6站位进行了CTD采水、箱式取样、重力取样及DCPT测试，D3站位因风浪过大，仅进行了CTD采水，未进行地质取样和测试；新增加9个泥火山调查站位，主要进行CTD采水及箱式取样工作。",
                        "obsSectNumber": null,
                        "partDictPersonNumber": null,
                        "partPersonNumber": 28,
                        "partUntisNumber": null,
                        "retaProgressId": null,
                        "southLat": "18°",
                        "standNumber": 18,
                        "startDate": "2016-05-01",
                        "startTime": "",
                        "surshipInfoId": "1",
                        "timeStandardId": 1,
                        "westLong": "114°"
                    },
                    {
                        "caryItemNumber": 26,
                        "cruiseId": 9,
                        "cruiseSegmentName": "2016年度吕宋海峡—南海海盆综合航次第二航段",
                        "cruiseSegmentRemark": "",
                        "cruiseVoyage": "2081.2",
                        "dataChangeTime": null,
                        "dataContactId": "",
                        "dataEditId": 2,
                        "eastLong": "119°",
                        "editTime": "2020-05-14 17:33:40",
                        "endDate": "2016-06-07",
                        "execOrgId": null,
                        "id": 10,
                        "imagesInfoId": "148",
                        "impChiefId": "246",
                        "invgionAreaId": "南海北部及吕宋海峡",
                        "isDisplay": 0,
                        "mearLineLength": null,
                        "mearLineNumber": null,
                        "northLat": "23°",
                        "obsInfo": "航段过程中共完成57个站位的调查工作。其中CTD作业站位50个，共CTD投放111次、采水30吨，CTD采样水深超过2000m站位13个。使用VMP-500型湍流剖面仪完成47个站位的现场观测。另外，还完成了浮游动物垂直拖网、浮游植物垂直拖网和船基培养实验。收集到航段开始以来的大气总辐射、光合有效辐射、紫外辐射数据以及南海总悬浮颗粒物（TSP）及利用Denuder采样系统获得的样品。本航段获取了南海不同环境梯度的生物地球化学过程与参数，收集到细菌、古菌、胶体、同位素示踪等样品，进行固氮、有机质降解等培养实验，为我国南海的海洋物理-生物地球化学耦合研究提供丰富素材，有望进一步了解南海的动力、环境与生态过程。",
                        "obsSectNumber": 7,
                        "partDictPersonNumber": null,
                        "partPersonNumber": 56,
                        "partUntisNumber": null,
                        "retaProgressId": null,
                        "southLat": "17°",
                        "standNumber": 57,
                        "startDate": "2016-05-15",
                        "startTime": "",
                        "surshipInfoId": "1",
                        "timeStandardId": 1,
                        "westLong": "110°"
                    }
                ]

            }
        },
        watch: {},
        mounted() {

        },
        methods: {
            close() {
                this.$emit('close')
                this.visible = false;
            },
            setData(data) {
                this.basicCruiseInfo = data.basicCruiseInfo,
                    this.cruiseProjectInfo = data.cruiseProjectInfo,
                    this.cruiseSegmentInfos = data.cruiseSegmentInfos;
                this.visible = true;
            },
            show() {
                this.visible = true;
            }
        }
    }
</script>

<style lang="scss" scoped>
    $panel-width:1000px;
    .panel-title{
        border-bottom: 1px solid #62d3f0;
        padding: 4px;
    }
    .line {
        border-bottom:2px solid #2f6e7d8c;
        float: left;
        margin: 1px 1%;
        width: 98%;
    }

    .title {
        position: relative;
        color: white;
        font-size: 18px;
        text-align: left;
        float: left;
        margin-left: 10px;
    }

    .proinfo-icon {
        margin: 2px auto;
        display: block;
        font-size: 20px;
        float: left;
    }

    .close {
        color: #ffffffb5;
        width: 20px;
        height: 20px;
        position: absolute;
        right: 10px;
        font-size: 24px;
        cursor: pointer;
    }

    .part {
        width: $panel-width - 50;
        margin: 10px 20px;
        box-sizing: border-box
    }

    .pannel-warpper {
        width: 100%;
        float: left;
    }

    .info-part {
        float: left;
        width: $panel-width - 60;
        margin: 10px 10px;
        box-sizing: border-box
    }

    .wrapper {
        top: 100px;
        bottom: 100px;
        width: 1000px
    }

    .wrapper /deep/ .el-row {
        margin-bottom: 20px;

        &:last-child {
            margin-bottom: 0;
        }
    }

    .wrapper /deep/ .el-col {
        border-radius: 4px;
    }

    .grid-key {
        font-size: 14px;
        color: white;
        border-left: 2px solid whitesmoke;
        text-align: right;
        background: #164065;
        padding-right: 6px;
        height: 20px;
        line-height: 20px;
    }

    .grid-value {
        font-size: 14px;
        color: white;
        background: #214667;
        padding-left: 6px;
        text-align: left;
        line-height: 20px;
    }
</style>