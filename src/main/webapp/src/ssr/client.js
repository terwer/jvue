import "@babel/polyfill";
import Main from "../main";

global.renderClient = () => {
  const vm = Main();
  vm.$mount("#app");
};
