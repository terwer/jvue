<template>
  <b-container fluid>
    <HeaderTime />
    <!--<Header title="jvue" />-->
    <Body />
    <!--<Footer />-->
    <FriendLink />
  </b-container>
</template>

<script>
import HeaderTime from "./HeaderTime";
// import Header from "./Header";
import Body from "./Body";
// import Footer from "./Footer";
import FriendLink from "./FriendLink";

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
    // Header,
    Body,
    // Footer,
    FriendLink
  }
};
</script>

<style scoped>
@import "style.css";
</style>
