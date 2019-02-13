const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const VueSSRClientPlugin = require("vue-server-renderer/client-plugin");

let clientConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-client.js",
  output: {
    filename: "js/[name].[hash:6].js"
  },

  // 重要信息：这将 webpack 运行时分离到一个引导 chunk 中，
  // 以便可以在之后正确注入异步 chunk。
  // 这也为你的 应用程序/vendor 代码提供了更好的缓存。
  optimization: {
    splitChunks: {
      chunks: "all"
    }
  },

  plugins: [
    // 此插件在输出目录中
    // 生成 `vue-ssr-client-manifest.json`。
    new VueSSRClientPlugin()
  ]
});

// 开发环境额外配置
if (base.config.isProduction === "development") {
  clientConfig = merge(clientConfig, {
    devServer: {
      // host: "0.0.0.0",
      port: 8888
    }
  });
}

// 确保暴露出去后不被修改
const exportedClientConfig = clientConfig;
module.exports = exportedClientConfig;
