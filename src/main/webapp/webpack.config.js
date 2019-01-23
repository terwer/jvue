const VueLoaderPlugin = require("vue-loader/lib/plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const path = require("path");

module.exports = (env, argv) => {
  // CSS提取应该只用于生产环境
  // 这样我们在开发过程中仍然可以热重载
  const isProduction = argv.mode === "production";
  console.log("mode:" + argv.mode);
  const renderMode = argv.renderMode ? argv.renderMode : "client";
  console.log("renderMode:" + renderMode);

  let entryFile;
  let buildPath;
  let outputFilename;
  switch (renderMode) {
    case "ssr_client": {
      entryFile = "./src/ssr/client.js";
      buildPath = path.resolve(__dirname, "ssrclientdist");
      outputFilename = "js/client.js";
      break;
    }
    case "ssr_server": {
      entryFile = "./src/ssr/server.js";
      buildPath = path.resolve(__dirname, "ssrdist");
      outputFilename = "js/server-bundle.js";
      break;
    }
    default: {
      entryFile = "./src/app.js";
      buildPath = path.resolve(__dirname, "dist");
      outputFilename = "js/[name].[hash:6].js";
      break;
    }
  }

  console.log("buildPath:" + buildPath);
  console.log("entryFile:" + entryFile);
  console.log("outputFilename:" + outputFilename);

  return {
    // All your other custom config...
    node: {
      fs: "empty",
      module: "empty"
    },
    entry: entryFile,
    output: {
      filename: outputFilename,
      path: buildPath
    },
    module: {
      rules: [
        {
          test: /\.vue$/,
          exclude: /node_modules/,
          loader: "vue-loader"
        },
        {
          test: /\.js$/,
          exclude: /node_modules/,
          use: {
            loader: "babel-loader"
          }
        },
        {
          test: /\.css$/,
          use: [
            !isProduction ? "vue-style-loader" : MiniCssExtractPlugin.loader,
            "css-loader"
          ]
        },
        {
          test: /\.(png|svg|jpg|gif)$/,
          use: ["file-loader"]
        }
      ]
    },
    devServer: {
      open: false,
      hot: true,
      port: 8000
    },
    plugins: isProduction
      ? [
          new VueLoaderPlugin(),
          new HtmlWebpackPlugin({
            template: "./src/index.html"
          }),
          // CSS剥离
          new MiniCssExtractPlugin({
            filename:
              renderMode === "client" ? "common.[hash:6].css" : "common.css"
          })
        ]
      : [
          new VueLoaderPlugin(),
          new HtmlWebpackPlugin({
            template: "./src/index.html"
          }),
          // 热加载
          new webpack.HotModuleReplacementPlugin()
        ]
  };
};
