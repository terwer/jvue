<template>
  <div id="app">
    <img src="assets/logo.png" />
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      msg: "Welcome to Your Vue.js App,with parcel build"
    };
  }
};
</script>

<style lang="css">
#app {
    color: #56b983;
}
</style>
