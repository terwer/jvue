// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const clientConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-client.js",
  output: {
    filename: "js/[name].[chunkhash:6].js"
  }
});

module.exports = clientConfig;
