<template>
  <b-container fluid>
    <HeaderTime />
    <Header title="jvue" />
    <Body />
    <Footer />
    <FriendLink />
  </b-container>
</template>

<script>
import HeaderTime from "./HeaderTime.vue";
import Header from "./Header.vue";
import Body from "./Body.vue";
import Footer from "./Footer.vue";
import FriendLink from "./FriendLink.vue";

export default {
  name: "Index",
  // 钩子函数
  mounted() {
    const that = this;
    setTimeout(function() {
      console.log("加载站点配置");
      that.siteConfig = {};
    }, 2000);
  },
  data: function() {
    return {
      title: "Next Vue SSR Project for Java Nashorn Script engine",
      siteConfig: null
    };
  },
  components: {
    HeaderTime,
    Header,
    Body,
    Footer,
    FriendLink
  }
};
</script>

<style scoped>
@import "style.css";
</style>
