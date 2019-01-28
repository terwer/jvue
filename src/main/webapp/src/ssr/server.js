import "@babel/polyfill";
import Main from "../main";
import { createRenderer } from "vue-server-renderer";
const { renderToString } = createRenderer();

global.renderServer = () => {
  const vm = Main();
  vm.$mount("#app");
  var promise = renderToString(vm);
  console.log("Vue server render return promise");
  return promise;
};
