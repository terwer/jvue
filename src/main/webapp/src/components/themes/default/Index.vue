<template>
  <el-container>
    <el-header class="header-container boxshadow">
      <Header :title="title" />
    </el-header>
    <el-main>
      <el-container>
        <el-main>Main</el-main>
        <el-aside width="200px">Aside</el-aside>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
import Header from "./Header.vue";

export default {
  name: "Index",
  data: function() {
    return {
      title: "Index page"
    };
  },
  components: {
    Header
  }
};
</script>

<style scoped>
@import "style.css";

.header-container {
  height: 100% !important;
}
</style>
