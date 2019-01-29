import { isInNashorn } from "./commom/NashornUtil";
import Vue from "vue";
import App from "./App.vue";

// 引入路由
import router from "./router"; // import router 的router一定要小写， 不要写成Router, 否则报 can't match的报错

// 组件引用
import BootstrapVue from "bootstrap-vue";

// 组建注册
Vue.use(BootstrapVue);

// 非Nashorn环境组件
if (!isInNashorn()) {
  console.log("Register components without nashorn in main.js");
  // import uweb from 'vue-uweb'
  const uweb = import(/* webpackChunkName: "vue-uweb" */ "vue-uweb");
  uweb
    .then(resolve => {
      console.log("uweb register success");
      // console.log(resolve.default);
      Vue.use(resolve.default, {
        siteId: "4445524",
        // http://s11.cnzz.com/z_stat.php?id=SITEID&web_id=SITEID // 文字样式
        src: "http://s5.cnzz.com/stat.php?id=4445524&show=pic" // 图片样式
      });
    })
    .catch(rejected => {
      console.log("uweb load error:" + rejected);
    });
}

// 生产部署时候需要设置为false
Vue.config.productionTip = true;

export default () => {
  console.log("Main is starting");
  return new Vue({
    // 注入 router 到根Vue实例
    router,
    render: h => h(App)
  });
};

// ======================
// With store and router
// ======================
//
// import Vue from "vue";
// import App from "./App.vue";

// import createRouter from "./router";
// const router =createRouter();

// Vue.config.productionTip = false;
//
// export default ({ router, store }) => {
//     return new Vue({
//         store,
//         router,
//         render: h => h(App)
//     });
// };
