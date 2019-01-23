import Vue from "vue";
// import BootstrapVue from 'bootstrap-vue'
import App from "./App.vue";

// 生产部署时候需要设置为false
Vue.config.productionTip = true;

// Vue.use(BootstrapVue);

const vm = new Vue({
  render: h => h(App)
});
vm.$mount("#app");
