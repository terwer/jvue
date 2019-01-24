<template>
  <b-row>
    <b-col cols="2"></b-col>
    <b-col cols="8">
      <b-row>
        <b-col cols="8">
          <PostList />
        </b-col>
        <b-col cols="4">
          <Aside />
        </b-col>
      </b-row>
    </b-col>
    <b-col cols="2"></b-col>
  </b-row>
</template>

<script>
import PostList from "./PostList.vue";
import Aside from "./Aside.vue";

export default {
  name: "Body",
  components: {
    PostList,
    Aside
  }
};
</script>

<style scoped></style>
