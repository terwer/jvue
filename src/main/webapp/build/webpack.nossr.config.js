// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

console.log("webpack base=>", base);

const appConfig = merge(base.webpackCnfig, {
  entry: "./src/app.js",
  output: {
    filename: "js/[name].[hash:6].js"
  },
  devServer: {
    // host: "0.0.0.0",
    port: 8888
  }
});

console.log("webpack appConfig=>", appConfig);
console.log(
  "base.config.isProduction in appConfig=>",
  base.config.isProduction
);

module.exports = appConfig;
