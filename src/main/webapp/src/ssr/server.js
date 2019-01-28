import "@babel/polyfill";
import Main from "../main";
import { createRenderer } from "vue-server-renderer";
const { renderToString } = createRenderer();

global.vm = Main();
global.renderServer = () => {
  // vm.$mount("#app");
  var promise = renderToString(global.vm);
  console.log("Vue server render return promise");
  return promise;
};
