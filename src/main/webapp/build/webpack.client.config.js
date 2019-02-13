// const webpack = require("webpack");
const merge = require("webpack-merge");
const base = require("./webpack.base.config");

const VueSSRClientPlugin = require("vue-server-renderer/client-plugin");

const clientConfig = merge(base.webpackCnfig, {
  entry: "./src/entry-client.js",
  output: {
    filename: "js/[name].[chunkhash:6].js"
  },
  plugins: [new VueSSRClientPlugin()]
});

module.exports = clientConfig;
