import { createApp } from "../main";

global.renderClient = () => {
  const { vm } = createApp();
  vm.$mount("#app");
};
console.log("entry-client.js is running...");
