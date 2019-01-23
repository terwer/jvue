<template>
  <div id="index">
    <Header :title="title" />
  </div>
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
</style>
