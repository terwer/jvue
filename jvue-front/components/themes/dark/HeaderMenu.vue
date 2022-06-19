<template>
  <el-menu
    :default-active="activeIndex"
    mode="horizontal"
    class="el-menu-dark"
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
    <li class="el-menu-item">
      <div>
        <a href="http://www.terwergreen.com/admin">
          <i class="fa fa-sign-in"></i>登录
        </a>
      </div>
    </li>
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
          name: "文章",
          link: "/",
          icon: "fa fa-home"
        },
        {
          id: 2,
          name: "随笔",
          link: "/essay",
          icon: "fa fa-pencil"
        },
        /* {
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
              id: 101,
              name: "Java",
              link: "/java%20se",
              icon: "fa fa-file-text"
            },
            {
              id: 102,
              name: "Spring Boot",
              link: "/spring%20boot",
              icon: "fa fa-file-text"
            },
            {
              id: 103,
              name: "Spring Cloud",
              link: "/spring%20cloud",
              icon: "fa fa-file-text"
            }
          ],
          link: "/s"
        },
        */
        {
          id: 6,
          name: "笔记",
          link: "/note",
          icon: "fa fa-pencil"
        },

        {
          id: 7,
          name: "关于",
          link: "/about",
          icon: "fa fa-user"
        }
        /*
        {
          id: 8,
          name: "登录",
          link: "/m",
          icon: "fa fa-sign-in"
        }
        /*
        {
          id: 9,
          name: "注册",
          link: "/admin",
          icon: "fa fa-camera-retro"
        }
        */
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
.el-menu a {
  text-decoration: none;
}
.el-menu-item a {
  color: #909399;
  text-decoration: none;
}
.el-menu-item a:hover {
  color: #f3f3f3;
}
.el-menu--horizontal .el-menu {
  background: #212121 !important;
}
.el-menu--popup {
  background: #212121 !important;
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
.el-menu--horizontal > .el-menu-item {
  background: #212121 !important;
}
.el-menu--horizontal .el-menu .el-menu-item,
.el-menu--horizontal .el-menu .el-submenu__title {
  background-color: #212121;
}
.el-menu--horizontal .el-menu .el-menu-item:hover {
  background-color: #181818;
}
.el-menu-item i:hover {
  color: #f3f3f3;
}
.el-menu--horizontal > .el-menu-item:hover {
  background: #181818 !important;
}
.el-menu--horizontal > .el-menu-item a {
  color: #909399;
}
.el-menu--horizontal > .el-menu-item a:hover {
  color: #f3f3f3;
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #303133;
  color: #303133;
}
</style>
