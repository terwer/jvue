<template>
  <b-row>
    <b-col>
      <b-nav pills>
        <b-nav-item
          v-for="item in menuList"
          :key="item.id"
          :to="item.link"
          :active="active === item.name"
          @click="selected(item.name)"
          >{{ item.name }}</b-nav-item
        >
      </b-nav>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "HeaderMenu",
  data() {
    return {
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
          name: "登录",
          link: "/auth/login"
        },
        {
          id: 7,
          name: "注册",
          link: "/auth/register"
        }
      ],
      active: "首页"
    };
  },
  methods: {
    selected(name) {
      this.active = name;
    }
  }
};
</script>

<style scoped></style>
