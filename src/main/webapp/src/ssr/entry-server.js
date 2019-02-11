/* eslint-disable no-undef */
import { createApp } from "../main";

var hasOnServerRenderSuccess = require("../../src/util/lib")
  .hasOnServerRenderSuccess;
var hasOnServerRenderError = require("../../src/util/lib")
  .hasOnServerRenderError;

// 解构赋值
// 解构赋值
const { vm, router, store } = createApp();
const renderVueComponentToString = require("vue-server-renderer/basic.js");

global.renderServer = context => {
  console.log("renderServer in entry server");
  console.log(`===context start===`);
  console.log(JSON.stringify(context));
  console.log(`===context end===`);

  // 因为有可能会是异步路由钩子函数或组件，所以我们将返回一个 Promise，
  // 以便服务器能够等待所有的内容在渲染前就已经准备就绪。
  return new Promise((resolve, reject) => {
    // 设置服务器端router的位置
    // 给路由推一条记录，上面的{app,router}只是一个对象，没有走真正渲染那步，
    // 所以只有主动调用router.push()它才会执行这部分的代码，
    // 给我们匹配到我们要调用的这些组件
    router.push(context.url);
    console.log(`push context to router=>context.url:${context.url}`);

    // 等到router将可能的异步组件和钩子函数解析完
    // router.onReady基本上只有在服务端才会被用到，
    // 在路由记录被推进去的时候，路由所有的异步操作都做完的时候才会调用这个回调，
    // 比如在服务端被渲染的时候，获取一些数据的操作
    router.onReady(
      () => {
        const matchedComponents = router.getMatchedComponents();
        // 匹配不到的路由，执行reject函数，并返回 404
        if (!matchedComponents.length) {
          console.log("No matchedComponents");
          if (onServerRenderError) {
            onServerRenderError({
              status: 0,
              data: "No matchedComponents",
              msg: "404 Not Found"
            });
            return;
          }

          return resolve({
            status: 0,
            data: "No matchedComponents",
            msg: "404 Not Found"
          });
        }

        // 对所有匹配的路由组件调用 `asyncData()`
        Promise.all(
          matchedComponents.map(Component => {
            console.log("Server matched Component=>", Component.name);
            // console.log("Server matched Component", Component);
            if (Component.asyncData) {
              console.log("invoke Component.asyncData");
              return Component.asyncData({
                store
              });
            }
          })
        )
          .then(() => {
            // 在所有预取钩子(preFetch hook) resolve 后，
            // 我们的 store 现在已经填充入渲染应用程序所需的状态。
            // 当我们将状态附加到上下文，
            // 并且 `template` 选项用于 renderer 时，
            // 状态将自动序列化为 `window.__INITIAL_STATE__`，并注入 HTML。
            context.state = store.state;

            //Render the html string
            console.log("Render the html string");
            renderVueComponentToString(vm, context, (err, html) => {
              if (err) {
                console.log(`Error rendering to string=>${err}`);
                if (hasOnServerRenderError) {
                  onServerRenderError({
                    status: 0,
                    data: err,
                    msg: `500 Internal Server Error:renderVueComponentToString,context.url=>${
                      context.url
                    }`
                  });
                  return;
                }
                resolve({
                  status: 0,
                  data: err,
                  msg: "500 Internal Server Error:renderVueComponentToString"
                });
              }

              // Promise应该resolve渲染后的html
              console.log("Promise resolved success");
              //if (hasOnServerRenderSuccess) {
              onServerRenderSuccess({ status: 1, data: html, msg: "200 OK" });
              return;
              //}
              resolve({ status: 1, data: html, msg: "200 OK" });
            });
          })
          .catch(rejected => {
            console.log("Promise.all catch rejected=>", rejected);
            // if (hasOnServerRenderError) {
            onServerRenderError({
              status: 0,
              data: rejected,
              msg: "500 Internal Server Error:async data errr"
            });
            return;
            // }
            resolve({
              status: 0,
              data: rejected,
              msg: "500 Internal Server Error:async data errr"
            });
          });
      },
      err => {
        // 错误返回
        console.log("router.onReady error callback");
        console.log(err);
        if (hasOnServerRenderError) {
          onServerRenderError({
            status: 0,
            data: err,
            msg: "500 Internal Server Error:router.onReady error callback"
          });
          return;
        }
        reject({
          status: 0,
          data: err,
          msg: "500 Internal Server Error:router.onReady error callback"
        });
      }
    );
  });
};
console.log("entry-server.js is running...");
