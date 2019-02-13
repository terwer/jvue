// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const VueSSRServerPlugin = require("vue-server-renderer/server-plugin");

const serverConfig = merge(base.webpackCnfig, {
  // 将 entry 指向应用程序的 server entry 文件
  entry: "./src/entry-server.js",

  // 这允许 webpack 以 Node 适用方式(Node-appropriate fashion)处理动态导入(dynamic import)，
  // 并且还会在编译 Vue 组件时，
  // 告知 `vue-loader` 输送面向服务器代码(server-oriented code)。
  target: "node",

  // 对 bundle renderer 提供 source map 支持
  devtool: "source-map",

  output: {
    // filename: "server-bundle.js", // bundle方式无需此配置
    libraryTarget: "commonjs2"
  },
  externals: Object.keys(require("../package.json").dependencies),

  // 这是将服务器的整个输出
  // 构建为单个 JSON 文件的插件。
  // 默认文件名为 `vue-ssr-server-bundle.json`
  plugins: [new VueSSRServerPlugin()]
});

module.exports = serverConfig;
