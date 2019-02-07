import "@babel/polyfill";
import { createApp } from "../main";

global.renderClient = () => {
  let { app } = createApp();
  app.$mount("#app");
};
