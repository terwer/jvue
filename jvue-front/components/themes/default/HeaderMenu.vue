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
          <i :class="child.icon || ''"></i>
          {{ child.name }}
        </el-menu-item>
      </el-submenu>
      <el-menu-item v-else :key="item.id" :index="item.link">
        <i :class="item.icon || ''"></i>
        <nuxt-link :to="item.link">{{ item.name }}</nuxt-link>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script>
export default {
  name: "HeaderMenu",
  data() {
    return {
      activeIndex: "/",
      menuList: [
        {
          id: 1,
          name: "首页",
          link: "/",
          icon: "fa fa-home"
        },
        {
          id: 2,
          name: "随笔",
          link: "/cat/essay",
          icon: "fa fa-pencil"
        },
        {
          id: 3,
          name: "技巧",
          link: "/cat/skill",
          icon: "fa fa-bolt"
        },
        {
          id: 4,
          name: "工具",
          link: "/cat/tool",
          icon: "fa fa-wrench"
        },
        {
          id: 5,
          name: "专题",
          icon: "fa fa-paper-plane",
          children: [
            {
              id: 100,
              name: "专题一",
              link: "/java-se",
              icon: "fa fa-file-text"
            },
            {
              id: 102,
              name: "专题二",
              link: "/spring-boot",
              icon: "fa fa-file-text"
            },
            {
              id: 103,
              name: "专题三",
              link: "/spring-cloud",
              icon: "fa fa-file-text"
            }
          ],
          link: "/cat/special"
        },
        {
          id: 6,
          name: "关于",
          link: "/about",
          icon: "fa fa-user"
        },
        {
          id: 7,
          name: "登录",
          link: "/auth/login",
          icon: "fa fa-sign-in"
        },
        {
          id: 8,
          name: "注册",
          link: "/auth/register",
          icon: "fa fa-camera-retro"
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

<style scoped></style>
