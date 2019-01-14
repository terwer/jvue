<template>
  <div id="app">
    <img alt="Spring Boot Logo" src="./assets/icon-spring-boot.svg">
    <img alt="Vue logo" src="./assets/logo.png">
    <Main msg="Welcome to Next Spring boot with Vue.js App"/>
  </div>
</template>

<script>
import Main from './components/Main'

export default {
  name: 'app',
  components: {
    Main
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>