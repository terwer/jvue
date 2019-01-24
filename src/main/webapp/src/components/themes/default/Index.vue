<template>
  <b-container fluid>
    <Header />
    <Body />
    <Footer />
    <FriendLink />
  </b-container>
</template>

<script>
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
