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

// =============================
// Require compiled script
// =============================
require("../../ssrdist/js/server-bundle.js");

// =============================
// Test script start
// =============================
const promise = global.renderServer();
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
