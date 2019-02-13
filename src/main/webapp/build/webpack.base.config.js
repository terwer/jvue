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
    new HtmlWebpackPlugin({
      template: "./public/index.ejs",
      title: "Next Vue SSR Project for Java j2v8 Script engine",
      favicon: "./public/favicon.ico",
      inject: true
    })
  ]
};

// export config
const conf = {
  config: config,
  webpackCnfig: webpackCnfig
};

module.exports = conf;