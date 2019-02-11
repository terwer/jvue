<template>
  <b-container fluid>
    <!-- <HeaderTime />
    <Header title="jvue" />
    <Body />
    <Footer />
    <FriendLink /> -->
    <div id="ssrData">{{ item }}</div>
  </b-container>
</template>

<script>
/* eslint-disable vue/no-unused-components*/
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Body from "../components/themes/default/Body";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";

export default {
  name: "Index",
  asyncData({ store }) {
    // 触发 action 后，会返回 Promise
    console.log("Index page=> PostList asyncData");
    return store.dispatch("fetchItem");
  },
  computed: {
    // 从 store 的 state 对象中的获取 item。
    item() {
      console.log("PostList computed");
      // console.log("getPostList items from store:");
      // console.log(this.$store.state.items);
      console.log("getPostList data from store:");
      console.log(this.$store.state.items["getPostList"]);
      return this.$store.state.items["getPostList"];
    }
  },
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
