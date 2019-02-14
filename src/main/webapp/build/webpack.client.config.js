const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const HtmlWebpackPlugin = require("html-webpack-plugin");
const VueSSRClientPlugin = require("vue-server-renderer/client-plugin");

let clientConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-client.js",
  output: {
    filename: "js/[name].[hash:6].js"
  },

  node: {
    fs: "empty",
    module: "empty"
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
    new VueSSRClientPlugin(),

    // html模板注入
    new HtmlWebpackPlugin(
      Object.assign(
        {
          template: "./public/index.ejs",
          favicon: "./public/favicon.ico",
          inject: !base.config.isProduction, // 只有entry-client环境才inject
          minify: {
            removeComments: false,
            collapseWhitespace: false,
            removeAttributeQuotes: false
            // more options:// https://github.com/kangax/html-minifier#options-quick-reference
          },
          // necessary to consistently work with multiple chunks via CommonsChunkPlugin
          chunksSortMode: "dependency"
        },
        base.config.seo
      )
    )
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
