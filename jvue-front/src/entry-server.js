/**
 * 仅运行于服务器
 *
 *@author Terwer
 *@version 1.0
 *2019/2/27 10:00
 **/
import { getLogger } from "./util/logger";
const logger = getLogger("entry-server");
import { createApp } from "./app";

export default context => {
  // 因为有可能会是异步路由钩子函数或组件，所以我们将返回一个 Promise，
  // 以便服务器能够等待所有的内容在渲染前，
  // 就已经准备就绪。
  return new Promise((resolve, reject) => {
    createApp().then(resolveInstance => {
      const app = resolveInstance.app;
      const router = resolveInstance.router;

      // 设置服务器端 router 的位置
      router.push(context.url);

      // 等到 router 将可能的异步组件和钩子函数解析完
      router.onReady(() => {
        const matchedComponents = router.getMatchedComponents();
        // 匹配不到的路由，执行 reject 函数，并返回 404
        if (!matchedComponents.length) {
          return reject({ code: 404 });
        }

        // 对所有匹配的路由组件调用 `asyncData()`
        Promise.all(
          matchedComponents.map(Component => {
            if (Component.asyncData) {
              logger.info("调用asyncData获取数据");
              return Component.asyncData({
                to: router.currentRoute
              });
            }
          })
        )
          .then(res => {
            // 在所有预取钩子(preFetch hook) resolve 后，
            // 我们的 store 现在已经填充入渲染应用程序所需的状态。
            // 当我们将状态附加到上下文，
            // 并且 `template` 选项用于 renderer 时，
            // 状态将自动序列化为 `window.__INITIAL_STATE__`，并注入 HTML。
            // console.log("matchedComponents asyncData res=>", res);
            logger.info(
              "matchedComponents asyncData set res to window.__INITIAL_STATE__"
            );
            logger.debug(res);
            context.state = res;

            // Promise 应该 resolve 应用程序实例，以便它可以渲染
            resolve(app);
          })
          .catch(rejected => {
            logger.error("asyncData rejected=>");
            console.error(rejected);
          });
      }, reject);
    });
  });
};
