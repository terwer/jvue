<template>
  <el-menu
    :default-active="activeIndex"
    mode="horizontal"
    @select="handleSelect"
  >
    <template v-for="item in menuList">
      <el-submenu
        v-if="item.children && item.children.length > 0"
        :key="item.id"
        :index="item.link"
      >
        <template slot="title">
          <i :class="item.icon || ''"></i>
          {{ item.name }}
        </template>
        <el-menu-item
          v-for="child in item.children"
          :key="child.id"
          :index="item.link + child.link"
        >
          <nuxt-link :to="item.link + child.link">
            <i :class="child.icon || ''"></i>
            {{ child.name }}
          </nuxt-link>
        </el-menu-item>
      </el-submenu>
      <el-menu-item v-else :key="item.id" :index="item.link">
        <div>
          <nuxt-link :to="item.link">
            <i :class="item.icon || ''"></i>
            {{ item.name }}
          </nuxt-link>
        </div>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script>
export default {
  name: "HeaderMenu",
  data() {
    return {
      activeIndex: "/index",
      menuList: [
        {
          id: 1,
          name: "首页",
          link: "/",
          icon: "fa fa-home"
        },
        {
          id: 1,
          name: "笔记",
          link: "/note",
          icon: "fa fa-pencil"
        },
        {
          id: 1,
          name: "随笔",
          link: "/essay",
          icon: "fa fa-bolt"
        },
        {
          id: 2,
          name: "关于",
          link: "/about",
          icon: "fa fa-user"
        }
      ]
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
.el-menu {
  background: #fff !important;
}
.el-menu--horizontal > .el-menu-item {
  background: #fff !important;
}
.el-menu--horizontal > .el-submenu .el-submenu__title {
  background-color: #fff !important;
}
.el-menu--horizontal .el-menu .el-menu-item,
.el-menu--horizontal .el-menu .el-submenu__title {
  background-color: #fff;
}
.el-menu a {
  text-decoration: none;
}
.el-menu-item a {
  color: #909399;
  text-decoration: none;
}
.el-menu-item a:hover {
  color: #212121;
}
.el-menu-item {
  padding: 35px 35px 0 35px;
}
.el-submenu__title {
  padding: 0 15px 0 10px;
}
.el-menu li {
  position: relative;
}
.el-menu li div a {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  height: 100%;
  padding: 0 10px 0 10px;
}
</style>
