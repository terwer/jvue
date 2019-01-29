import "@babel/polyfill";
import { createApp } from "./main";

global.renderClient = () => {
  let { app } = createApp();
  const vm = app;
  vm.$mount("#app");
};
