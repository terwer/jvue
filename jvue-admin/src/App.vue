<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: "Microsoft Yahei", Helvetica, Arial, Verdana, Tahoma, sans-serif !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
