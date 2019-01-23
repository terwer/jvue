import "@babel/polyfill";
import Main from "../main";
import { createRenderer } from "vue-server-renderer";
const { renderToString } = createRenderer();

global.renderServer = () => {
  const vm = Main();
  var promise = renderToString(vm);
  console.log("Vue server render promise:" + promise);
  return promise;
};
