/**
 * server.js
 * npm run babel src/server.js -o dist/server.js
 *@author Terwer
 *@version 1.0
 *2019/2/27 12:05
 **/
const fs = require("fs");
const path = require("path");
const { createBundleRenderer } = require("vue-server-renderer");

// In production: create server renderer using built server bundle.
// The server bundle is generated by vue-ssr-webpack-plugin.
// 路径相对于 dist 文件夹
// noinspection JSFileReferences
const template = fs.readFileSync(
  path.resolve(__dirname, "./index.ssr.html"),
  "utf-8"
);

// noinspection JSFileReferences
const serverBundle = require("./vue-ssr-server-bundle.json");
// noinspection JSFileReferences
const clientManifest = require("./vue-ssr-client-manifest.json");
const renderer = createBundleRenderer(serverBundle, {
  runInNewContext: false, // 推荐
  template, // （可选）页面模板
  clientManifest // （可选）客户端构建 manifest
});

/**
 * 渲染服务
 * @param context 上下文
 * @param renderServerCallback 回调
 */
const renderServer = (context, renderServerCallback) => {
  const contextObj = JSON.parse(context);
  const promise = renderer.renderToString(contextObj);
  if (typeof renderServerCallback === "undefined") {
    return promise;
  }
  // 如果有callback，执行callback
  console.log("callback exists,calling callback...");
  promise
    .then((resolve, reject) => {
      if (reject) {
        renderServerCallback(reject);
        return;
      }
      console.log("renderServer resolve=>", resolve);
      // 在dom中查找body
      // const dom = new JSDOM(resolve);
      // const placeholder = dom.window.document.querySelector("placeholder");
      // console.log("renderServer placeholder=>", placeholder.innerHTML);
      // renderServerCallback(null, placeholder.innerHTML);
      renderServerCallback(null, resolve);
    })
    .catch(rejected => {
      renderServerCallback(rejected);
    });
};

module.exports = {
  renderServer
};
