<template>
  <b-container fluid>
    <b-row class="shadow-sm p-0 mb-0 bg-white rounded">
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <Header :title="title" />
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <b-row>
          <b-col cols="8">
            <PostList />
          </b-col>
          <b-col cols="4">Aside</b-col>
        </b-row>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
    <b-row>
      Footer
    </b-row>
  </b-container>
</template>

<script>
import Header from "./Header.vue";
import PostList from "./PostList.vue";

export default {
  name: "Index",
  data: function() {
    return {
      title: "Index page"
    };
  },
  components: {
    Header,
    PostList
  }
};
</script>

<style scoped>
@import "style.css";
</style>
