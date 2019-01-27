<template>
  <b-row id="headerTime">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col>
      现在是 {{ clientTime }}
      <span class="next-line"><br /></span>
      星期五 农历 腊月二十一 天气晴
    </b-col>
    <b-col sm="0" md="2"></b-col>
  </b-row>
</template>

<script>
export default {
  name: "HeaderTime",
  mounted() {
    this.clientTime = this.getClientTime();
  },
  watch: {
    clientTime(val) {
      console.log("this.clientTime changed:" + val);
    }
  },
  data() {
    return {
      clientTime: "2019年01月25日 23:27:01"
    };
  },
  methods: {
    getClientTime() {
      //获取客户端时间
      var now = new Date();
      var currDatetime = now.getFullYear() + "年";
      currDatetime +=
        now.getMonth() + 1 > 9
          ? now.getMonth() + 1
          : "0" + (now.getMonth() + 1);
      currDatetime += "月";
      currDatetime += now.getDate() > 9 ? now.getDate() : "0" + now.getDate();
      currDatetime += "日 ";
      currDatetime +=
        now.getHours() > 9 ? now.getHours() : "0" + now.getHours();
      currDatetime += ":";
      currDatetime +=
        now.getMinutes() > 9 ? now.getMinutes() : "0" + now.getMinutes();
      currDatetime += ":";
      currDatetime +=
        now.getSeconds() > 9 ? now.getSeconds() : "0" + now.getSeconds();
      return currDatetime;
    }
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
