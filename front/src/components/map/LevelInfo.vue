<template>
    <transition name="el-zoom-in-top">
        <div v-if="visible" class="abs-vc wrapper">
            <SqurePanel>
                <div class="pannel-warpper">
                    <div v-for="ele in elements" :key="ele.key" class="part-wrapper">
                        <div :id="ele.key + '_chart'" class="part left"></div>
                    </div>
                </div>
                <div class="close" @click="close">×</div>
            </SqurePanel>
        </div>
    </transition>

</template>

<script>
    import SqurePanel from "@/baseUI/SqurePanel"
    import echarts from "echarts"

    export default {
        name: "proinfo",
        components: {
            SqurePanel
        },
        props: {},
        data() {
            return {
                visible: false,
                elements: [{
                    title: "",
                    key: "",
                    unit: "",
                    xKey: "",
                    yKey: ""
                }],
                datas: []
            }
        },
        watch: {},
        mounted() {

        },
        methods: {
            close() {
                this.$emit('close')
                this.visible = false;
                this.elements = []
            },
            setData(elements, datas) {
                this.visible = true;
                this.elements = elements;
                this.datas = datas;
                this.$nextTick(() => {
                    this.useData();
                })
            },
            useData() {
                let baseOption = {
                    title: {
                        text: '',
                        x: 0,
                        textAlign: 'left',
                        textStyle: {
                            align: 'center',
                            color: '#fff',
                            fontSize: 16,
                            fontWeight: '100',
                        },
                    },
                    backgroundColor: "rgba(2,13,34,1)",
                    tooltip: {
                        trigger: 'axis',
                        backgroundColor: 'rgba(0,0,0,0.5)'
                    },
                    grid: {
                        top: 30,
                        left: 10,
                        right: 50,
                        bottom: 5,
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        scale: true,
                        name: "",
                        color: '#fff',
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: '#fff',
                            }
                        },
                        axisLabel: {
                            color: '#fff'
                        },
                        axisTick: {
                            show: false
                        },
                    },
                    yAxis: {
                        type: 'category',
                        name: "",
                        //inverse: true,
                        splitLine: {
                            show: true,
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: '#fff',
                            }
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',
                            },
                            formatter: function(value) {
                                return value;
                            }
                        },
                        axisTick: {
                            show: false,
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#fff',
                                type: 'dashed',
                            }
                        }
                    },
                    series: [{
                        name: '',
                        type: 'line',
                        smooth: true, //是否平滑
                        showAllSymbol: true,
                        symbol: 'circle',
                        symbolSize: 10,
                        lineStyle: {
                            normal: {
                                color: "#fff"
                            },
                        },
                        label: {
                            show: false,
                            position: 'top',
                            textStyle: {
                                color: '#fff',
                            }
                        },

                        itemStyle: {
                            color: "#FFF",
                            borderColor: "#48B3FF",
                            borderWidth: 2,

                        },
                        tooltip: {
                            show: true
                        },
                        data: [55, 35, 62, 55, 97, 64, 44, 66, 78, 82, 33, 77],
                    }]
                };
                this.elements.forEach((ele) => {
                    let dom = ele.key + "_chart";
                    let chart = echarts.init(document.getElementById(dom));
                    let xdata = this.datas.map((item) => {
                        return item[ele.xKey]
                    })
                    let ydata = this.datas.map((item) => {
                        return item[ele.yKey] + " m"
                    })
                    baseOption.xAxis.name = ele.unit;
                    baseOption.title.text = ele.title;
                    baseOption.yAxis.data = ydata.reverse();
                    baseOption.xAxis.data = xdata.reverse();
                    baseOption.series[0].data = xdata
                    chart.setOption(baseOption);
                })
            },
            show() {
                this.visible = true;
            }
        },
        beforeDestroy() {
            this.elements = []
        }
    }
</script>

<style lang="scss" scoped>
    $panel-width:1000px;

    .part-wrapper {
        width: 320px;
        margin: 4px;
        float: left;
    }

    .panel-title {
        border-bottom: 1px solid #62d3f0;
        padding: 4px;
    }

    .line {
        border-bottom: 2px solid #2f6e7d8c;
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
        width: 100%;
        box-sizing: border-box;
        height: 600px;
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