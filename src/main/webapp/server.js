const renderVueComponentToString = require("vue-server-renderer/basic.js");
const serverBundle = require("./dist/server-bundle");

console.log("server is running ...");

const context = {
  url: "/"
};
const promise = serverBundle.default(context);

promise
  .then((resolve, reject) => {
    console.log("promise resolved");
    const vm = resolve;
    renderVueComponentToString(vm, (err, res) => {
      if (err) {
        console.log(err);
        return;
      }
      console.log(res);
    });
  })
  .catch(rejected => {
    console.log(rejected);
  });
