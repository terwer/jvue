// babel test/v8-server.js --presets=@babel/preset-env
// ====================================
// test server
// ====================================
const render = require("../dist/server").default;

// get context
const seo = {
  title: "title",
  meta: {
    keywords: "keywords",
    description: "description"
  }
};
const context = Object.assign({ url: "/" }, seo);

// deal with promise
var promise = render(context);
promise
  .then((resolve, reject) => {
    if (reject) {
      console.log(`reject=> 
${reject}`);
      return;
    }
    console.log(`resolve=>
${resolve}`);
  })
  .catch(rejected => {
    console.log(`rejected=>
${rejected}`);
  });
