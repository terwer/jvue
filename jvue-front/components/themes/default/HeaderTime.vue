<template>
  <el-row>
    <el-col :xs="24" :sm="24" :md="6">
      <div>现在是 {{ clientTime }} {{ weekday }}</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12">
      <div>{{ popTime === "" ? "加载中..." : popTime }}</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="6">
      <div>{{ tradTime === "" ? "加载中..." : tradTime }}</div>
    </el-col>
  </el-row>
</template>

<script>
import {
  getClientTime,
  getWeekByDay,
  getPopTime,
  getShengXiao,
  getTradTime
} from "../../../util/DateUtil";

export default {
  name: "HeaderTime",
  data() {
    return {
      clientTime: "",
      weekday: "",
      popTime: "",
      tradTime: "",
      shengxiao: ""
    };
  },
  // 钩子函数
  mounted() {
    const that = this;
    that.clientTime = getClientTime();
    that.weekday = getWeekByDay();
    that.popTime = getPopTime();
    that.tradTime = getTradTime();
    that.shengxiao = getShengXiao();
    setInterval(function() {
      that.clientTime = getClientTime();
    }, 1000);
  }
};
</script>

<style scoped lang="scss"></style>
