// =============================
// This is polyfill not in node
// =============================
//
// 模拟window
var window = {
  navigator: {
    userAgent: "Chrome"
  }
};
global.window = window;

global.inBrowser = false;

// =============================
// Require compiled script
// =============================
require("../../ssrdist/js/server-bundle.js");

// =============================
// Test script start
// =============================
const context = {
  title: "vue ssr",
  meta: `<meta charset="utf-8"/>`,
  url: "/p/1.html"
};

const promise = global.renderServer(context);
// console.log(promise);
promise.then(
  resolve => {
    console.log("result:resolve");
    console.log(resolve);
  },
  rejected => {
    console.log("rejected:resolve");
    console.log(rejected);
  }
);
