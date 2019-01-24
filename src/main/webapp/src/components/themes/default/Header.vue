<template>
  <el-container id="header">
    <el-aside class="logo">
        <a href="/" title="terwer">
          <img src="./images/logo.png" alt="terwer" />
        </a>
    </el-aside>
    <el-main class="header-main">
      menu
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "Header",
  props: {
    title: {
      type: [String],
      default() {
        return "";
      }
    }
  },
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>

<style scoped>
#header {
  background: #fff;
  height: 75px;
  padding: 0 !important;
  color: #666;
  margin: 0 auto;
  overflow-x: hidden;
}

@media (min-width: 1200px) {
  #header {
    width: 1200px !important;
  }
}

#header .logo {
  width: 175px !important;
  margin-right: 15px;
}

#header .logo img{
  padding: 0;
  max-height: 40px;
  margin-top: 20px;
}

#header .header-main {
  padding: 0;
  line-height: 75px;
}
</style>
