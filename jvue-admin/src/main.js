import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Api from "./api";

import useElementUI from "./plugins/element-ui";

Vue.config.productionTip = false;
Vue.prototype.$api = Api;
useElementUI();

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
