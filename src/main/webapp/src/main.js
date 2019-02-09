import Vue from "vue";
import App from "./App.vue";

export function createApp() {
  // 创建 router 实例
  // const router = createRouter();
  const vm = new Vue({
    // 注入 router 到根 Vue 实例
    // router,
    render: h => h(App)
  });
  //注入和导出router
  // 返回 vm 和 router
  return { vm };
  // return { vm, router };
}
