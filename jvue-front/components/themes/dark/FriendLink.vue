<template>
  <el-row id="friendLink">
    <el-col :xs="24" :md="4" class="friendLinkBox">
      <h2>友情链接</h2>
    </el-col>
    <el-col :xs="24" :md="20">
      <div class="text-left">
        <ul class="footerbottom">
          <li>
            <a href="http://www.terwergreen.com" target="_blank">远方的灯塔</a>
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
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "FriendLink"
};
</script>

<style scoped lang="scss">
#friendLink {
  background: #2e3033;
  color: rgba(255, 255, 255, 0.42);
  box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.1);
}
.friendLinkBox {
  text-align: right;
}
@media screen and (max-width: 600px) {
  .friendLinkBox {
    text-align: left;
    margin-left: 20px;
  }
}
#friendLink h2 {
  font-size: 15px;
  color: rgba(255, 255, 255, 0.42);
  font-weight: 600;
  display: inline-block;
  margin-right: 5px;
}
.footerbottom {
  text-align: left;
}
@media screen and (max-width: 600px) {
  .footerbottom {
    padding-left: 20px;
    line-height: 10px;
    margin-bottom: 20px;
  }
}
.footerbottom a {
  color: #ffcb6b !important;
  text-decoration: none;
}
.footerbottom a:hover {
  color: #c3e88d !important;
}
.footerbottom li {
  display: inline-block;
  margin-right: 15px;
  margin-bottom: 5px;
}
</style>
