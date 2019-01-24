import Vue from "vue";
import App from "./App.vue";

// 生产部署时候需要设置为false
Vue.config.productionTip = true;

export default () => {
  console.log("Main is starting");
  return new Vue({
    render: h => h(App)
  });
};

// ======================
// With store and router
// ======================
//
// import Vue from "vue";
// import App from "./App.vue";
//
// Vue.config.productionTip = false;
//
// export default ({ router, store }) => {
//     return new Vue({
//         store,
//         router,
//         render: h => h(App)
//     });
// };
