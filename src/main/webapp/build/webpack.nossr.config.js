const { VueLoaderPlugin } = require("vue-loader");
const HtmlWebpackPlugin = require("html-webpack-plugin");
module.exports = {
  mode: "development",
  node: {
    fs: "empty",
    module: "empty"
  },
  entry: "./src/app.js",
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
  ],
  devServer: {
    host: "0.0.0.0",
    port: 8888
  }
};
