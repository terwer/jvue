<template>
  <b-container fluid>
    <HeaderTime />
    <Header title="jvue" />

    <b-row>
      <b-col sm="0" md="0" lg="0" xl="2"></b-col>
      <b-col>
        <b-breadcrumb :items="items" />
        <div id="postContent">
          <h1 class="text-center">This is detail {{ postId }}</h1>
        </div>
      </b-col>
      <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    </b-row>

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
  name: "Detail",
  computed: {
    postId() {
      return this.$route.params.id;
    }
  },
  watch: {
    $route(to, from) {
      // to表示的是你要去的那个组件，from 表示的是你从哪个组件过来的，它们是两个对象，你可以把它打印出来，它们也有一个param 属性
      console.log(to);
      console.log(from);
    }
  },
  components: {
    HeaderTime,
    Header,
    Footer,
    FriendLink
  },
  data() {
    return {
      items: [
        {
          text: "首页",
          href: "/"
        },
        {
          text: "文章",
          active: true
        }
      ]
    };
  }
};
</script>

<style scoped>
.breadcrumb {
  margin-top: 10px;
}
#postContent {
}
</style>
