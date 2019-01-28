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
  data: function() {
    return {
      title: "Next Vue SSR Project for Java Nashorn Script engine"
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
