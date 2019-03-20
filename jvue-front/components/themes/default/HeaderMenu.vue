<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item index="1">处理中心</el-menu-item>
    <el-submenu index="2">
      <template slot="title"
        >我的工作台</template
      >
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
      <el-menu-item index="2-3">选项3</el-menu-item>
      <el-submenu index="2-4">
        <template slot="title"
          >选项4</template
        >
        <el-menu-item index="2-4-1">选项1</el-menu-item>
        <el-menu-item index="2-4-2">选项2</el-menu-item>
        <el-menu-item index="2-4-3">选项3</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-menu-item index="3" disabled>消息中心</el-menu-item>
    <el-menu-item index="4">
      <nuxt-link to="/default">默认首页</nuxt-link>
    </el-menu-item>
    <el-menu-item index="5">登录</el-menu-item>
    <el-menu-item index="6">注册</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "HeaderMenu",
  data() {
    return {
      activeIndex: "1",
      menuList: [
        {
          id: 1,
          name: "首页",
          link: "/"
        },
        {
          id: 2,
          name: "随笔",
          link: "/cat/essay"
        },
        {
          id: 3,
          name: "技巧",
          link: "/cat/skill"
        },
        {
          id: 4,
          name: "工具",
          link: "/cat/tool"
        },
        {
          id: 5,
          name: "专题",
          link: "/cat/special"
        },
        {
          id: 6,
          name: "关于",
          link: "/about"
        },
        {
          id: 7,
          name: "登录",
          link: "/auth/login"
        },
        {
          id: 8,
          name: "注册",
          link: "/auth/register"
        }
      ],
      active: "首页"
    };
  },
  methods: {
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
};
</script>

<style scoped></style>
