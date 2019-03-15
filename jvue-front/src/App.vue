<template>
  <div id="app">
    <router-view />
    <!-- set progressbar -->
    <vue-progress-bar></vue-progress-bar>
  </div>
</template>

<script>
import { getLogger } from "./util/logger";
const logger = getLogger("App");
import { inBrowser } from "./util/dom";

export default {
  name: "App",
  // 钩子函数
  mounted() {
    if (inBrowser) {
      window.onresize = () => {
        return (() => {
          window.screenWidth = document.body.clientWidth;
          logger.info("resize  window.screenWidth to " + window.screenWidth);
        })();
      };
    }
  }
};
</script>

<style lang="scss">
#app {
  font-family: "Microsoft YaHei", sans-serif, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
