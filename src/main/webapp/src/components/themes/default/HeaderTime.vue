<template>
  <b-row id="headerTime">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col>
      现在是
      <span v-if="clientTime === ''"> 加载中... </span>
      <span v-else> {{ clientTime }}</span>
      <span class="next-line"><br /></span>
      <span v-if="clientTime === ''"> 加载中... </span>
      <span v-else>{{ tradTime }}</span>
      天气晴
    </b-col>
    <b-col sm="0" md="2"></b-col>
  </b-row>
</template>

<script>
import { getClientTime, getTradTime } from "../../../commom/DateUtil";
export default {
  name: "HeaderTime",
  // 钩子函数
  mounted() {
    let that = this;
    that.clientTime = getClientTime();
    that.tradTime = getTradTime();
    setInterval(function() {
      that.clientTime = getClientTime();
    }, 2000);
  },
  data() {
    return {
      clientTime: "",
      tradTime: ""
    };
  }
};
</script>

<style scoped>
/*
##Device = iPad
##Screen = B/w 768px - 1023px
*/
@media (min-width: 768px) and (max-width: 1023px) {
  .next-line {
    display: none;
  }
}
/*
##Device = iPad Pro
##Screen = B/w 1024px - 1365px
*/
@media (min-width: 1024px) and (max-width: 1365px) {
  .next-line {
    display: none;
  }
}
/*
##Device = PC
##Screen = B/w 1366px
*/
@media (min-width: 1366px) {
  .next-line {
    display: none;
  }
}
</style>
