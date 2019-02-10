// sudo npm install -g @babel/core @babel/cli @babel/node @babel/preset-env @babel/preset-flow
// babel test.js --presets=@babel/preset-env,@babel/flow
// node test.js
// import "./templates/entry-server";
// (() => {
//   const context = {
//     url: "/about"
//   };
//   const promise = global.renderServer(context);
//   console.log("promise=>", promise);
//   return promise;
// })();

"use strict";

require("./templates/entry-server");

// sudo npm install -g @babel/core @babel/cli @babel/node @babel/preset-env @babel/preset-flow
// babel test.js --presets=@babel/preset-env,@babel/flow
// node test.js
(function() {
  var context = {
    url: "/about"
  };
  var promise = global.renderServer(context);
  console.log("promise=>", promise);
  return promise;
})();
