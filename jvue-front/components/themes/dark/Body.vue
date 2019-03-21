<template>
  <el-row>
    <el-col :xs="24" :md="16">
      <PostList :post-list="postList" />
    </el-col>
    <el-col :xs="24" :md="8">
      <Aside />
    </el-col>
  </el-row>
</template>

<script>
import PostList from "./PostList";
import Aside from "./Aside";
export default {
  name: "Body",
  components: { Aside, PostList },
  props: {
    postList: {
      type: Array,
      default: () => []
    }
  }
};
</script>

<style scoped lang="scss"></style>
