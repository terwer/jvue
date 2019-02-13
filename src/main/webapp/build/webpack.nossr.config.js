// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const appConfig = merge(base.webpackCnfig, {
  entry: "./src/nossr.js",
  output: {
    filename: "js/[name].[hash:6].js"
  },
  devServer: {
    // host: "0.0.0.0",
    port: 8888
  }
});

module.exports = appConfig;
