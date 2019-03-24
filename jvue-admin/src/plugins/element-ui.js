import Vue from "vue";
import Element from "element-ui";
import locale from "element-ui/lib/locale/lang/zh-CN";
import "element-ui/lib/theme-chalk/index.css";

Vue.use(Element, { locale });
console.log("Register element-ui success");

export default {};
