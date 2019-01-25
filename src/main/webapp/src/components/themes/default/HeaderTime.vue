<template>
  <b-row id="headerTime">
    <b-col sm="0" md="2"></b-col>
    <b-col>
      现在是 2019年01月25日 23:27:01
      <span class="next-line"><br /></span>
      星期五 农历 腊月二十一 天气晴
    </b-col>
    <b-col sm="0" md="2"></b-col>
  </b-row>
</template>

<script>
export default {
  name: "HeaderTime"
};
</script>

<style scoped>
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
