<template>
  <!-- 友情链接 -->
  <b-row id="friendLink">
    <b-col xl="2"></b-col>
    <b-col>
      <ul class="footerbottom">
        <li><h2>友情链接</h2></li>
        <li>
          <a href="/" target="_blank">远方的灯塔</a>
        </li>
        <li>
          <a href="https://www.pengjiandry.com/" target="_blank">彭涧</a>
        </li>
        <li>
          <a href="http://lusongsong.com/" target="_blank">卢松松博客</a>
        </li>
        <li>
          <a href="/guestbook.html">申请友链</a>
        </li>
      </ul>
    </b-col>
    <b-col xl="2"></b-col>
  </b-row>
</template>

<script>
export default {
  name: "FriendLink"
};
</script>

<style scoped>
#friendLink {
  background: #2e3033;
  color: rgba(255, 255, 255, 0.42);
  box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.1);
}
.footerbottom {
  padding: 15px 0;
}
.footerbottom a {
  color: #666 !important;
}
.footerbottom h2 {
  font-size: 15px;
  color: rgba(255, 255, 255, 0.42);
  font-weight: 600;
  display: inline-block;
  margin-right: 5px;
}
.footerbottom li {
  display: inline-block;
  margin-right: 15px;
  margin-bottom: 5px;
}
/*
##Device = Mobile
##Screen = B/w 320px - 767px
*/
@media (min-width: 320px) and (max-width: 767px) {
  .footerbottom li {
    display: block;
  }
}
</style>
