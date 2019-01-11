<template>
  <div id="app">
    <img alt="spring-boot-icon" src="https://spring.io/img/homepage/icon-spring-boot.svg">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Next Spring boot with Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  components: {
    HelloWorld
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