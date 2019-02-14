// ====================================
// dev server with express
// ====================================

const path = require("path");
const resolve = file => path.resolve(__dirname, file);

const express = require("express");
const app = express();
const port = 3000;

// 静态资源
const favicon = require("serve-favicon");
const serve = (path, cache) => express.static(resolve(path));

const render = require("../dist/server");

app.use(favicon("./public/favicon.ico"));
app.use("/js", serve("../dist/js", false));

// handle http requests
app.get("*", (req, res) => {
  // get context
  const seo = {
    title: "title",
    meta: {
      keywords: "keywords",
      description: "description"
    }
  };
  const context = JSON.stringify(Object.assign({ url: req.url }, seo));

  render
    .renderServer(context)
    .then((resolve, reject) => {
      if (reject) {
        console.log("reject");
        res.send(reject);
        return;
      }
      console.log("resolve");
      res.send(resolve);
    })
    .catch(rejected => {
      console.log("rejected");
      res.send(rejected);
    });
});

// start http server
app.listen(port, () => {
  console.log(`dev-server is listening on port ${port}!`);
});
