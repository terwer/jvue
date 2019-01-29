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

// // =============================
// // Require compiled script
// // =============================
// //
// eval("../../ssrdist/js/server-bundle.js");
//
// // =============================
// // Test script start
// // =============================
// //
// var promise = global.renderServer();
// console.log("renderServer finished")
// console.log(promise)

// =================
// Usage
// =================
require("../../ssrdist/js/server-bundle.js");

const promise = global.renderServer();

promise.then(
    value => {
        console.log(value); // Success!
    },
    reason => {
        console.log(reason); // Error!
    }
);