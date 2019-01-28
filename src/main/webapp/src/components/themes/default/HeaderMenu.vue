<template>
  <b-row>
    <b-col>dfgdf</b-col>
    <b-col
      md="4"
      lg="4"
      xl="2"
      class="justify-content-center align-self-center"
    >
      <div class="header-right">
        <div class="loginlink">
          <a href="/auth/login"><i class="fa fa-user-circle"></i>登录</a>
          <a href="/auth/register"><i class="fa fa-pencil-square-o"></i>注册</a>
        </div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "HeaderMenu"
};
</script>

<style scoped>
/* 右侧登录按钮 */
.loginlink a {
  padding: 0.5rem;
}
</style>
