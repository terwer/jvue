/**
 * v8-server.js
 *
 *@author Terwer
 *@version 1.0
 *2019/2/27 15:01
 **/
// 设置渲染模式
process.env.SSR_ENV = "server";
process.env.VUE_ENV = "server";
process.env.NODE_ENV = "production";
process.on("unhandledRejection", function(reason, p) {
  console.log("Unhandled Rejection at: Promise", p, "reason:", reason);
});

const CircularJSON = require("circular-json");

const render = require("../dist/server");

// get context
const seo = {
  title: "title",
  meta: {
    keywords: "keywords",
    description: "description"
  }
};
const context = CircularJSON.stringify(Object.assign({ url: "/" }, seo));

// deal with callback
global.renderServerCallback = (err, html) => {
  // 处理异常……
  if (err) {
    console.log("err=>", err);
    return;
  }
  console.log("html=>", html);
};

global.setSessionCallback = (key, value) => {
  console.log("key=>", key);
  console.log("value=>", value);
};

global.getSessionCallback = key => {
  const value = CircularJSON.stringify([]);
  console.log("getSessionCallback key=>", key);
  console.log("getSessionCallback value=>", value);
  return value;
};

render.renderServer(context, global.renderServerCallback);
