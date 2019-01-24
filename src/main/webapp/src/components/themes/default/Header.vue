<template>
  <b-container id="header">
    <b-row>
      <b-col cols="*">
        <div class="imgWrap">
          <div class="align-middle">
            <a href="/"><img class="logo" src="./images/logo.png" alt="Terwer"/></a>
          </div>
        </div>
      </b-col>
      <b-col cols="*" class="justify-content-center align-self-center">
        <b-nav pills>
          <b-nav-item active>Active</b-nav-item>
          <b-nav-item>Link</b-nav-item>
          <b-nav-item-dropdown
            id="nav7_ddown"
            text="Dropdown"
            extra-toggle-classes="nav-link-custom"
            right
          >
            <b-dropdown-item>one</b-dropdown-item>
            <b-dropdown-item>two</b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item>three</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-nav>
      </b-col>
    </b-row>
  </b-container>

  <!--
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
  -->
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
    return {};
  },
  methods: {}
};
</script>

<style scoped>
/**
* 图片居中
*/
#header .imgWrap .align-middle {
  height: 75px;
  text-align: center;
}
#header .imgWrap .align-middle:before {
  content: " ";
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}

#header .imgWrap .logo {
  display: inline-block;
  max-height: 40px;
}
</style>
