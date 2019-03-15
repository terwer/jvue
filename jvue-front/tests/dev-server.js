/**
 * dev server with express
 *
 *@author Terwer
 *@version 1.0
 *2019/2/27 15:01
 **/
// 设置渲染模式
process.env.VUE_ENV = "server";
process.on("unhandledRejection", function(reason, p) {
  console.log("Unhandled Rejection at: Promise", p, "reason:", reason);
});

const CircularJSON = require("circular-json");

const path = require("path");
const resolvePath = file => path.resolve(__dirname, file);

const express = require("express");
const port = 3000;

// Configure your application to use Thymeleaf via the express-thymeleaf module
let app = express();
const render = require("../dist/server");

// 静态资源
// const favicon = require("serve-favicon");
const serve = path => express.static(resolvePath(path));
// app.use(favicon("./public/favicon.ico"));
// app.use("/", (req, res, next) => {
//   if (process.env.NODE_ENV === "development") {
//     const result = req.url.match(/\.(html)$/);
//       console.log("req.url=>",req.url)
//       console.log("result=>",result)
//     if (result) {
//       return res.status(403).end("403 Forbidden");
//     }
//   }
//   next();
// });
app.use("/", serve("../dist", false));
app.use("/js", serve("../dist/js", false));
app.use("/css", serve("../dist/css", false));
app.use("/img", serve("../dist/img", false));

// 在服务器处理函数中……
app.get("*", (req, res) => {
  // get context
  const seo = {
    title: "title",
    meta: {
      keywords: "keywords",
      description: "description"
    }
  };
  const context = CircularJSON.stringify(Object.assign({ url: req.url }, seo));

  // 这里无需传入一个应用程序，因为在执行 bundle 时已经自动创建过。
  // 现在我们的服务器与应用程序已经解耦！
  const promise = render.renderServer(context);

  promise
    .then((html, err) => {
      if (err) {
        console.log("err=>", err);
        res.send(err);
        return;
      }
      console.log("html=>", html);
      res.send(html);
    })
    .catch(rejected => {
      console.log("rejected=>", rejected);
      res.send(rejected);
    });
});

// start http server
app.listen(port, () => {
  console.log(`dev-server is listening on port ${port}...`);
});

// deal with callback
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
