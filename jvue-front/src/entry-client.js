/**
 * 仅运行于浏览器
 *
 *@author Terwer
 *@version 1.0
 *2019/2/27 10:00
 **/
import { getLogger } from "./util/logger";
const logger = getLogger("entry-client");
import { getSession } from "./util/storage";
import { createApp } from "./app";

// CSS只在客户端引用
// import "bootstrap/dist/css/bootstrap.css";
// import "bootstrap-vue/dist/bootstrap-vue.css";
// import "v-toaster/dist/v-toaster.css";

// 公共样式库
import(/* webpackChunkName: "bootstrap-style" */ "bootstrap/dist/css/bootstrap.css");
import(/* webpackChunkName: "bootstrap-vue-style" */ "bootstrap-vue/dist/bootstrap-vue.css");
import(/* webpackChunkName: "v-toaster-style" */ "v-toaster/dist/v-toaster.css");
// import(/* webpackChunkName: "vue-hljs-style" */ "vue-hljs/dist/vue-hljs.min.css");
import(/* webpackChunkName: "vue-hljs-style" */ "./lib/vue-hljs/vs.css");

// 自定义样式库
// import "components/themes/default/style.css";
import(/* webpackChunkName: "jvue-style" */ "./components/themes/default/style.css");

// 后台管理地址
const ADMIN_PATH = process.env.VUE_APP_ADMIN_PATH;

// 客户端特定引导逻辑……
createApp().then(resolve => {
  const app = resolve.app;
  const router = resolve.router;

  // wait until router has resolved all async before hooks
  // and async components...
  router.onReady(() => {
    // Add router hook for handling asyncData.
    // Doing it after initial route is resolved so that we don't double-fetch
    // the data that we already have. Using router.beforeResolve() so that all
    // async components are resolved.
    router.beforeResolve((to, from, next) => {
      app.$Progress.start();

      logger.info("to=>");
      console.log(to);
      logger.info("from=>");
      console.log(from);

      const matched = router.getMatchedComponents(to);
      const prevMatched = router.getMatchedComponents(from);
      logger.info("matched=>", matched);
      logger.info("prevMatched=>", prevMatched);
      let diffed = false;

      //使用钩子函数对路由进行权限跳转
      const role = getSession("ms_username");
      console.log("role=>", role);
      const adminLoginPath = ADMIN_PATH + "/login";
      if (!role && to.path !== adminLoginPath) {
        return next(adminLoginPath);
      }

      // 查找当前活动的组件
      const activated = matched.filter((component, i) => {
        return diffed || (diffed = prevMatched[i] !== component);
      });
      if (!activated.length) {
        app.$Progress.finish();
        return next();
      }

      // 对所有匹配的路由组件调用 `asyncData()`
      Promise.all(
        activated.map(Component => {
          if (Component.asyncData) {
            logger.info("调用asyncData获取数据");
            return Component.asyncData({
              from: from,
              to: to
            });
          } else {
            logger.info("未找到asyncData");
          }
        })
      )
        .then(res => {
          app.$Progress.finish();
          // 这里的结果会保存在SessionStorage
          logger.debug("matchedComponents asyncData res=>", res);
          next();
        })
        .catch(rejected => {
          app.$toaster.error(rejected);
          next();
        });
    });
  });

  // 这里假定 App.vue 模板中根元素具有 `id="app"`
  app.$mount("#app");
});
