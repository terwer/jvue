// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

console.log("webpack base=>", base);

const serverConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-server.js",
  output: {
    filename: "server-bundle.js"
  }
});

console.log("webpack serverConfig=>", serverConfig);

module.exports = serverConfig;
