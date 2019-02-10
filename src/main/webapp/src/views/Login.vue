<template>
  <b-container fluid>
    <HeaderTime />
    <Header title="jvue" />
    <h1 class="text-center">This is login page</h1>
    <Footer />
    <FriendLink />
  </b-container>
</template>

<script>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";

export default {
  name: "Login",
  components: {
    HeaderTime,
    Header,
    Footer,
    FriendLink
  }
};
</script>

<style scoped></style>
