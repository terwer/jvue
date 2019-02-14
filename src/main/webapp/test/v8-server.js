// babel test/v8-server.js --presets=@babel/preset-env
// ====================================
// test server
// ====================================
const render = require("../dist/server");

// get context
const seo = {
  title: "title",
  meta: {
    keywords: "keywords",
    description: "description"
  }
};
const context = JSON.stringify(Object.assign({ url: "/" }, seo));

// deal with callback
var renderServerCallback = (err, html) => {
  if (err) {
    console.log(err);
    return;
  }
  console.log("html=>", html);
};

render.renderServer(context, renderServerCallback);

// // deal with promise
// var promise = render.renderServer(context);
// promise
//   .then((resolve, reject) => {
//     if (reject) {
//       console.log(`reject=>${reject}`);
//       return;
//     }
//     console.log(`resolve=>${resolve}`);
//   })
//   .catch(rejected => {
//     console.log(`rejected=>${rejected}`);
//   });
