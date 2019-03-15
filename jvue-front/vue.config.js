/**
 * vue.config.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-26 下午10:40
 **/
const pkg = require("./package.json");
const nodeExternals = require("webpack-node-externals");
const VueSSRClientPlugin = require("vue-server-renderer/client-plugin");
const VueSSRServerPlugin = require("vue-server-renderer/server-plugin");

module.exports = {
  /**
   * 部署应用包时的基本 URL。用法和 webpack 本身的 output.publicPath 一致，
   * 但是 Vue CLI 在一些其他地方也需要用到这个值，所以请始终使用 publicPath
   * 而不要直接修改 webpack 的 output.publicPath。
   */
  publicPath: process.env.VUE_APP_PUBLIC_PATH,
  /**
   * 是否在开发环境下通过 eslint-loader 在每次保存时 lint 代码。
   * https://cli.vuejs.org/zh/config/#lintonsave
   */
  lintOnSave: true,

  // 指定生成的 index.html 的输出路径 (相对于 outputDir)。
  // 也可以是一个绝对路径
  // 默认index.html，修改之后，只有生产环境才会生效
  // indexPath: "index.html",

  // https://cli.vuejs.org/zh/config/#configurewebpack
  configureWebpack: config => {
    console.log("\nprocess.env.SSR_ENV=>", process.env.SSR_ENV);
    switch (process.env.SSR_ENV) {
      case "client": {
        // 客户端配置 (Client Config)
        // https://ssr.vuejs.org/zh/guide/build-config.html#客户端配置-client-config
        console.log("客户端配置");

        // 将 entry 指向应用程序的 client entry 文件
        config.entry = "./src/entry-client.js";

        // 重要信息：这将 webpack 运行时分离到一个引导 chunk 中，
        // 以便可以在之后正确注入异步 chunk。
        // 这也为你的 应用程序/vendor 代码提供了更好的缓存。
        //webpack4的用法：https://github.com/webpack/webpack/issues/6357
        // config.optimization = {
        //   splitChunks: {
        //     chunks: "all"
        //   }
        // };

        // 此插件在输出目录中
        // 生成 `vue-ssr-client-manifest.json`。
        config.plugins.push(new VueSSRClientPlugin());
        break;
      }
      case "server": {
        // 服务器配置 (Server Config)
        // https://ssr.vuejs.org/zh/guide/build-config.html#服务器配置-server-config
        console.log("服务器配置");

        // 将 entry 指向应用程序的 server entry 文件
        config.entry = "./src/entry-server.js";

        // 这允许 webpack 以 Node 适用方式(Node-appropriate fashion)处理动态导入(dynamic import)，
        // 并且还会在编译 Vue 组件时，
        // 告知 `vue-loader` 输送面向服务器代码(server-oriented code)。
        config.target = "node";

        // 对 bundle renderer 提供 source map 支持
        config.devtool = "source-map";

        // 此处告知 server bundle 使用 Node 风格导出模块(Node-style exports)
        config.output.libraryTarget = "commonjs2";

        // https://webpack.js.org/configuration/externals/#function
        // https://github.com/liady/webpack-node-externals
        // 外置化应用程序依赖模块。可以使服务器构建速度更快，
        // 并生成较小的 bundle 文件。
        config.externals = nodeExternals({
          // 不要外置化 webpack 需要处理的依赖模块。
          // 你可以在这里添加更多的文件类型。例如，未处理 *.vue 原始文件，
          // 你还应该将修改 `global`（例如 polyfill）的依赖模块列入白名单
          whitelist: /\.(css|sass)$/
        });

        // 这是将服务器的整个输出
        // 构建为单个 JSON 文件的插件。
        // 默认文件名为 `vue-ssr-server-bundle.json`
        config.plugins.push(new VueSSRServerPlugin());
        break;
      }
      default: {
        // serve模式
        console.log("serve模式");

        // config.resolve.symlinks(true);
        config.devServer = {
          disableHostCheck: true
        };

        // 两种配置都可以
        config.entry = "./src/entry-client.js";
        // config.entry = {
        //   app: ["./src/entry-client.js"]
        // };
        break;
      }
    }

    if (process.env.NODE_ENV === "production") {
      // 为生产环境修改配置...
    } else {
      // 为开发环境修改配置...
    }
  },
  // https://cli.vuejs.org/zh/config/#chainwebpack
  chainWebpack: config => {
    // 配置html-webpack-plugin
    config.plugin("html").tap(args => {
      // inject为true会自动在html文件中添加js和css引用
      args[0].inject = true;
      args[0].template = "./public/index.html";
      args[0].favicon = "./public/favicon.ico";
      args[0].title = pkg.name.concat(" v").concat(pkg.version);
      args[0].ssrEnv = process.env.SSR_ENV;
      args[0].debug = process.env.VUE_APP_DEBUG;
      args[0].minify = {
        //压缩HTML文件
        removeComments: false, //保留HTML中的注释
        collapseWhitespace: process.env.NODE_ENV === "production" //删除空白符与换行符
      };
      return args;
    });
  }
};
