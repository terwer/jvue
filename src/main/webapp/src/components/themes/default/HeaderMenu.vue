<template>
    <b-row>
        <b-col>
            <b-nav pills>
                <b-nav-item to="/" active>首页</b-nav-item>
                <b-nav-item>随笔</b-nav-item>
                <b-nav-item>技巧</b-nav-item>
                <b-nav-item>工具</b-nav-item>
                <b-nav-item>专题</b-nav-item>
                <b-nav-item to="/auth/login">登录</b-nav-item>
                <b-nav-item to="/auth/register">注册</b-nav-item>
            </b-nav>
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
.loginlink {
  padding-bottom: 10px;
}
</style>
