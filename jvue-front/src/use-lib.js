/**
 * use-lib.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-28 上午12:36
 **/
import { getLogger } from "./util/logger";
const logger = getLogger("use-lib");
import { inBrowser } from "./util/dom";
import Vue from "vue";

// 引入通用组件
import BootstrapVue from "bootstrap-vue";
Vue.use(BootstrapVue);
logger.debug("Register bootstrap-vue success");

import vueHljs from "vue-hljs";
Vue.use(vueHljs);
logger.debug("Register vue-hljs success");

import ElementUI from "element-ui";
Vue.use(ElementUI, {
  size: "small"
});
logger.debug("Register element-ui success");

import "url-search-params-polyfill";
logger.debug("Register url-search-params-polyfill success");

// 浏览器专用
if (inBrowser) {
  const Toaster = require("v-toaster");
  // console.log("Toaster=>", Toaster);
  // 持续时间，默认10秒
  Vue.use(Toaster, { timeout: 2000 });
  logger.info("Register Toaster success");

  // import VueProgressBar from "vue-progressbar";
  const VueProgressBar = require("vue-progressbar");
  const options = {
    color: "#00a4ff",
    failedColor: "#ff6b68",
    thickness: "2px",
    transition: {
      speed: "0.2s",
      opacity: "0.6s",
      termination: 300
    },
    autoRevert: true,
    location: "top",
    inverse: false
  };
  Vue.use(VueProgressBar, options);
  logger.info("Register VueProgressBar success");

  // admin需要使用的库
} else {
  // 服务端专用
}

/**
 * 动态引用依赖库
 * @returns {Promise<any>}
 */
export const useLib = () => {
  // 引入特定组件
  return new Promise((resolve, reject) => {
    // 浏览器环境专用组件
    if (inBrowser) {
      // 非动态注册
      const uweb = require("vue-uweb").default;
      Vue.use(uweb, {
        siteId: "4445524",
        // http://s11.cnzz.com/z_stat.php?id=SITEID&web_id=SITEID // 文字样式
        src: "http://s5.cnzz.com/stat.php?id=4445524&show=pic" // 图片样式
      });
      logger.debug("Register uweb success");

      // 动态注册
      // import Storage from 'vue-web-storage'
      const StoragePromise = import(/* webpackChunkName: "vue-web-storage" */ "vue-web-storage");

      Promise.all([StoragePromise])
        .then(function(values) {
          // Storage
          const Storage = values[0];
          Vue.use(Storage.default, {
            prefix: "jvue_", // default `app_`
            drivers: ["session", "local"] // default 'local'
          });
          logger.debug("Register vue-web-storage success");

          return resolve("浏览器环境组件注册成功");
        })
        .catch(rejected => {
          logger.error(`Register components rejected=>${rejected}`);
          return reject(rejected);
        });
    } else {
      return resolve("服务端环境组件注册完成");
    }
  });
};
