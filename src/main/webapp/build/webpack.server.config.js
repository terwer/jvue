// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const serverConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-server.js",
  output: {
    filename: "server-bundle.js"
  }
});

module.exports = serverConfig;
