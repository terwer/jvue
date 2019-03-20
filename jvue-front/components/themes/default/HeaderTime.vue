<template>
  <el-row class="time">
    <el-col :xs="24" :sm="24" :md="8">
      <div>现在是 {{ clientTime }} {{ weekday }}</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="8">
      <div>{{ popTime === "" ? "加载中..." : popTime }}</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="8">
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

<style scoped lang="scss">
.time {
  line-height: 30px;
  text-align: left;
  padding: 10px;
}
</style>
