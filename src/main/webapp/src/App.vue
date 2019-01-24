<template>
  <div id="app">
    <Index />
  </div>
</template>

<script>
import Index from "./components/themes/default/Index.vue";

export default {
  name: "App",
  components: {
    Index
  }
};
</script>

<style scoped>
#app {
  font-family: "Microsoft YaHei", sans-serif;
}
</style>
