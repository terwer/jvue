import Vue from "vue";
import Element from "~/plugins/lib/element-ui";
import locale from "~/plugins/lib/element-ui/lib/locale/lang/zh-CN";
import { getLogger } from "../util/logger";
import "~/plugins/lib/element-ui/lib/theme-chalk/index.css";
const logger = getLogger("element-ui");

export default () => {
  Vue.use(Element, { locale });
  logger.info("Register element-ui success");
};
