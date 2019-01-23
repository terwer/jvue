import "@babel/polyfill";
import { createRenderer } from "vue-server-renderer";
const { renderToString } = createRenderer();

import Vue from "vue";
import App from "../App.vue";
// import BootstrapVue from 'bootstrap-vue'

// 生产部署时候需要设置为false
Vue.config.productionTip = false;

// Vue.use(BootstrapVue);

global.renderServer = () => {
  const vm = new Vue({
    render: h => h(App)
  });
  var promise = renderToString(vm);
  console.log("Vue server render promise:" + promise);
  return promise;
};
