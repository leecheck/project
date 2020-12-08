<template>
  <div v-show="legendFlag" class="absoluteContainer tlegend">
    <div class="relativeContainer percent100" style="background-color:#ffffffe6;border-radius: 5px;color: #0a233b;">
      <div class="legTitle">图 例</div>
      <div class="legContent">
        <div v-for="(legendList,key) in legends" v-bind:key='key' class="left" style="width:100%">
          <div class="itemTitle">{{key}}</div>  
          <div class="itemContent" v-for="(i,index) in legendList" v-bind:key="index">
            <i v-if="i.type == 'fa'" :style="{'background-color': i.color ,'border':'1px solid white'}"
              class="icon-fa icon left"></i>
            <div v-if="i.type == 'fill'" :style="{'background-color': i.color ,'border':'1px solid #d9c2c2'}"
              class="fill left"></div>
            <div v-if="i.type == 'img'" class="left" style="height:22px"><img :src="i.src" class="icon" style="border: 1px solid #d9c2c2; height: 12px;width: 18px;" /></div>
            <div v-if="i.type == 'point'" class="left" style="height:22px;overflow:hidden"><img :src="i.src" class="icon" style="height: 18px;width: 12px;margin-right:5px" /></div>
            <div style="" class="left ellipse">{{i.name}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  let legendConf = {};
  export default {
    name: 'tlegend',
    components: {},
    mounted() {

    },
    data() {
      return {
        legends: {},
        legendFlag: false
      }
    },
    methods: {
      addLegend(name) {
        let legendName = name;
        let legendList = [];
        let co = legendConf;
        if (legendConf.hasOwnProperty(name)) {
          if(legendConf[name] instanceof Array){
            legendList = legendConf[name];
          }else{
            legendList = [legendConf[name]];
          }
          this.$set(this.legends, legendName, legendList)
        }
      },
      removeLegend(name) {
        this.$delete(this.legends, name);
      },
      legend(flag) {
        this.legendFlag = flag;
      },
      close(){
        this.legendFlag = false;
      }
    },
    computed: {

    },
    watch: {},
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
  @lengend-windth: 160px;

  .tlegend {
    width: @lengend-windth;
    height: 270px;
    position: absolute;
    bottom: 0px;
    overflow-x: hidden;
    overflow-y: auto
  }

  .legTitle {
    height: 25px;
    line-height: 25px;
    background-color: #042E68e6;
    color: #FFFFFF;
    border-radius: 5px 5px 0 0;
    font-size: 14px;
  }

  .icon-close {
    font-size: 25px;
    float: right;
    margin-right: 0px;
    cursor: pointer;
    position: absolute;
    right: 0px;
  }

  .legContent {
    position: absolute;
    padding: 4px 0;
    overflow-y: auto;
    top: 25px;
    bottom: 0px;
    left: 0px;
    right: 0px;
  }

  .itemTitle {
    height: 20px;
    line-height: 20px;
    margin: 2px 4px;
    background-color: #c9e8fde6;
    color: #3496f1;
    font-size: 13px;
  }

  .itemContent {
    height: 25px;
    text-align: left;
    margin: 0px 2px;
    font-size: 12px;
    line-height: 25px;
    width: @lengend-windth - 10;
    overflow-x: hidden;
  }

  .icon {
    width: 20px;
    margin: 5px 2px;
  }

  .icon-fa {
    color: white;
    // background-color: blue;
    border-radius: 10px;
    height: 18px;
    width: 18px;
    line-height: 22px;
    text-align: center;
    border: 3px solid #ffffff !important;
    box-shadow: 3px 3px 3px !important;
  }

  .icon-line {
    font-size: 18px;
    margin-right: 0px;
  }

  .fill {
    width: 20px;
    height: 13px;
    margin-top: 6px;
  }

</style>
<style>

</style>
