<template>
  <div class="header">
    <div class="collapse">
      <a @click="collapse"><span class="icon-th-list"></span></a>
    </div>
    <h3 class="title">
      <img src="../../assets/logo.png" alt="logo" />
      Jvue
    </h3>
    <ul class="header-right">
      <li>
        <a class="preview" target="_blank" :href="frontUrl">
          <span class="el-icon-view"></span>
          <span>预览</span>
        </a>

        <a @click="logout">
          <span class="icon-sign-in"></span>
          <span>退出</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      frontUrl: "http://www.terwergreen.com"
    };
  },
  methods: {
    collapse: function() {
      this.$root.$emit("collapse");
    },
    logout() {
      this.$api.auth.logout().then(data => {
        if (data.status === 1) {
          this.$message({
            type: "success",
            message: "登出成功!"
          });
          this.$router.push("/login");
        } else {
          this.$message({
            type: "error",
            message: data.msg || "登出失败!"
          });
          this.$router.push("/login");
        }
      });
    }
  }
};
</script>

<style scoped>
.header {
  width: 100%;
  height: 60px;
  display: inline-block;
  background-color: #fff;
  line-height: 60px;
  text-align: center;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
}

.collapse {
  display: none;
  float: left;
  width: 64px;
}

.title {
  margin: 0;
  display: inline-block;
  text-transform: uppercase;
}

.title img {
  vertical-align: middle;
  max-height: 45px;
}

.header-right {
  list-style: none;
  float: right;
  margin: 0 35px 0 0;
  color: #7f8c8d;
}

.header-right a {
  color: #7f8c8d;
  cursor: pointer;
  text-decoration: none;
}

.preview {
  margin-right: 20px;
}

@media screen and (max-width: 600px) {
  .collapse {
    display: block;
  }
    .title{
        width: 120px;
    }
}
</style>
