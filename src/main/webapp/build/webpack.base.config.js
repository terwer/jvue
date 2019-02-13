const { VueLoaderPlugin } = require("vue-loader");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const config = require("../config");

console.log("process.env.NODE_ENV=>" + process.env.NODE_ENV);
console.log("config.isProduction=>" + config.isProduction);

const webpackCnfig = {
  mode: process.env.NODE_ENV,
  node: {
    fs: "empty",
    module: "empty"
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: "vue-loader"
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin(),
    new HtmlWebpackPlugin(
      Object.assign(
        {
          template: "./public/index.ejs",
          favicon: "./public/favicon.ico",
          inject: !config.isProduction, // 只有开发环境才inject
          minify: {
            removeComments: false,
            collapseWhitespace: false,
            removeAttributeQuotes: false
            // more options:// https://github.com/kangax/html-minifier#options-quick-reference
          },
          // necessary to consistently work with multiple chunks via CommonsChunkPlugin
          chunksSortMode: "dependency"
        },
        config.seo
      )
    )
  ]
};

// export config
const conf = {
  config: config,
  webpackCnfig: webpackCnfig
};

module.exports = conf;
