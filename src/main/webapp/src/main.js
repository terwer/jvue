import Vue from "vue";
import App from "./App.vue";
import { createRouter } from "./router";
import { createStore } from "./store";
import { sync } from "vuex-router-sync";

// 组件引用
import BootstrapVue from "bootstrap-vue";

// 组建注册
Vue.use(BootstrapVue);

Vue.config.productionTip = false;

export function createApp() {
  // 创建 router 和 store 实例
  const router = createRouter();
  const store = createStore();

  // 同步路由状态(route state)到 store
  sync(store, router);

  const vm = new Vue({
    router,
    store,
    render: h => h(App)
  });

  // 暴露 vm, router 和 store。
  return { vm, router, store };
}
