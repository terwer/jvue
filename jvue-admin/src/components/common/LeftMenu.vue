<template>
  <div
    :class="[showLeftMenu ? 'left-menu-show' : 'left-menu-hide']"
    class="left-menu"
  >
    <el-menu
      default-active=""
      background-color="#324157"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
      @select="mobileToggle"
    >
      <el-menu-item index="/dashboard">
        <span class="icon-dashboard"></span>&nbsp;
        <span slot="title">仪表盘</span>
      </el-menu-item>
      <el-menu-item index="/post/publish">
        <span class="icon-edit"></span>&nbsp;
        <span slot="title">发布文章</span>
      </el-menu-item>
      <el-menu-item index="/post?page=1">
        <span class="icon-list"></span>&nbsp;
        <span slot="title">文章列表</span>
      </el-menu-item>
      <el-menu-item index="/comment?page=1">
        <span class="icon-comments-o"></span>
        <span slot="title">评论列表</span>
      </el-menu-item>
      <el-menu-item index="/tag">
        <span class="icon-tags"></span>&nbsp;
        <span slot="title">标签/分类</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title"
          >自定义页面</template
        >
        <el-menu-item index="/page/publish">
          <span class="icon-folder"></span>&nbsp;
          <span slot="title">新增页面</span>
        </el-menu-item>
        <el-menu-item index="/page?page=1">
          <span class="icon-th-list"></span>&nbsp;
          <span slot="title">页面列表</span>
        </el-menu-item>
      </el-submenu>

      <el-menu-item index="/setting">
        <span class="icon-cog"></span>&nbsp;
        <span slot="title">网站设置</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showLeftMenu: false
    };
  },
  methods: {
    toggleLeftMenu() {
      this.showLeftMenu = !this.showLeftMenu;
    },
    mobileToggle() {
      if (document.body.clientWidth < 600) {
        this.showLeftMenu = false;
      }
    }
  },
  created() {
    this.$root.$on("collapse", this.toggleLeftMenu);
  }
};
</script>

<style>
.el-submenu .el-menu-item {
  min-width: auto !important;
}
</style>

<style scoped>
.left-menu {
  position: fixed;
  top: 60px;
  bottom: 0;
  left: 0;
  width: 150px;
  background-color: #324157;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
  transition: 0.3s left;
}

.left-menu .el-menu {
  border-right: none;
}

.left-menu-show {
  left: 0;
}

.left-menu-hide {
  left: -151px;
}

@media screen and (min-width: 600px) {
  .left-menu {
    left: 0;
  }
}
</style>
