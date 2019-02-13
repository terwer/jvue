// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

console.log("webpack base=>", base);

const clientConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-client.js",
  output: {
    filename: "js/[name].[chunkhash:6].js"
  }
});

console.log("webpack clientConfig=>", clientConfig);

module.exports = clientConfig;
