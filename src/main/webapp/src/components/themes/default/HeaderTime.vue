<template>
  <b-row id="headerTime">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col>
      <b-row>
        <b-col md="6" lg="6" xl="3" v-if="clientTime === ''"> 加载中... </b-col>
        <b-col md="6" lg="6" xl="3" v-else
          >现在是 {{ clientTime }} {{ weekday }}</b-col
        >

        <b-col md="6" lg="6" xl="3" v-if="popTime === ''"> 加载中... </b-col>
        <b-col md="6" lg="6" xl="3" v-else class="item">{{ popTime }}</b-col>

        <b-col md="6" lg="6" xl="3" v-if="tradTime === ''"> 加载中... </b-col>
        <b-col md="6" lg="6" xl="3" v-else class="item">{{ tradTime }}</b-col>

        <b-col md="6" lg="6" xl="3" v-if="shengxiao === ''"> 加载中... </b-col>
        <b-col md="6" lg="6" xl="3" v-else class="item"
          >生肖：{{ shengxiao }}</b-col
        >
      </b-row>
    </b-col>
    <b-col sm="0" md="2"></b-col>
  </b-row>
</template>

<script>
import {
  getClientTime,
  getWeekByDay,
  getPopTime,
  getShengXiao,
  getTradTime
} from "../../../commom/DateUtil";
export default {
  name: "HeaderTime",
  // 钩子函数
  mounted() {
    let that = this;
    that.clientTime = getClientTime();
    that.weekday = getWeekByDay();
    that.popTime = getPopTime();
    that.tradTime = getTradTime();
    that.shengxiao = getShengXiao();
    setInterval(function() {
      that.clientTime = getClientTime();
    }, 2000);
  },
  data() {
    return {
      clientTime: "",
      weekday: "",
      popTime: "",
      tradTime: "",
      shengxiao: ""
    };
  }
};
</script>

<style scoped></style>
