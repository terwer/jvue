import Vue from "vue";
import App from "./App.vue";
import { createRouter } from "./router";

// 组件引用
import BootstrapVue from "bootstrap-vue";

// 组建注册
Vue.use(BootstrapVue);

Vue.config.productionTip = false;

export function createApp() {
  // 创建 router 实例
  const router = createRouter();
  const vm = new Vue({
    // 注入 router 到根 Vue 实例
    router,
    render: h => h(App)
  });
  // 返回 vm 和 router
  return { vm, router };
}
