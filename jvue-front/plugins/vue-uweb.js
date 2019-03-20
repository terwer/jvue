/**
 * vue-uweb
 *
 *@author Terwer
 *@version 1.0
 *2019/3/20 16:25
 **/
import Vue from "vue";
import uweb from "vue-uweb";
import { getLogger } from "../util/logger";
const logger = getLogger("plugins/vue-uweb");

export default () => {
  Vue.use(uweb, {
    siteId: "4445524",
    // http://s11.cnzz.com/z_stat.php?id=SITEID&web_id=SITEID // 文字样式
    src: "http://s5.cnzz.com/stat.php?id=4445524&show=pic" // 图片样式
  });
  logger.info("Register uweb success");
};
