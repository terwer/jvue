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
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Body from "../components/themes/default/Body";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";

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

<style scoped></style>
