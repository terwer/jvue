import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Api from "./api";
import axios from "axios";
import dayjs from "dayjs";
import util from "./util/util";

import "./plugins/element-ui";

// 时间过滤器
Vue.filter("time", function(data, fmt) {
  fmt = fmt || "YYYY-MM-DD hh:mm";
  return dayjs(data).format(fmt);
});

Vue.config.productionTip = false;
Vue.prototype.$api = Api;
Vue.prototype.$axios = axios;
Vue.prototype.$dayjs = dayjs;
Vue.prototype.$util = util;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
