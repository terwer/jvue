const VueLoaderPlugin = require("vue-loader/lib/plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const path = require("path");

module.exports = (env, argv) => {
  // CSS提取应该只用于生产环境
  // 这样我们在开发过程中仍然可以热重载
  const isProduction = argv.mode === "production";
  const renderMode = argv.renderMode ? argv.renderMode : "client";
  const isClientServe = renderMode === "client" && !isProduction;
  const isClient = renderMode === "client" || renderMode === "ssr_client";

  console.log("mode:" + argv.mode);
  console.log("renderMode:" + renderMode);
  console.log("isClientServe:" + isClientServe);
  console.log("isClient:" + isClient);

  let entryFile;
  let buildPath;
  let outputFilename;
  let webpackPlugins;
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
      outputFilename = "[name].[hash:6].js";
      break;
    }
  }

  console.log("buildPath:" + buildPath);
  console.log("entryFile:" + entryFile);
  console.log("outputFilename:" + outputFilename);

  webpackPlugins = [
    new VueLoaderPlugin(),
    new HtmlWebpackPlugin({
      template: "./src/index.ejs",
      title: "Next Vue SSR Project for Java Nashorn Script engine",
      favicon: "./public/favicon.ico",
      inject: true
    }),
    // CSS剥离
    new MiniCssExtractPlugin({
      filename:
        renderMode === "client" ? "css/common.[hash:6].css" : "css/common.css"
    })
  ];

  if (isClientServe) {
    // 热加载
    console.log("Hot reload is open");
    const HotModuleReplacementPlugin = new webpack.HotModuleReplacementPlugin();
    webpackPlugins.push(HotModuleReplacementPlugin);
  } else {
    if (isProduction) {
      // 压缩css
      console.log("OptimizeCSSAssets is open");
      const cssAssetsPlugin = new OptimizeCSSAssetsPlugin({
        assetNameRegExp: /\.css$/g,
        cssProcessor: require("cssnano"),
        cssProcessorPluginOptions: {
          preset: [
            "default",
            {
              discardComments: {
                removeAll: true
              }
            }
          ]
        },
        canPrint: true
      });
      webpackPlugins.push(cssAssetsPlugin);
    }
  }

  let webpackConfig = {
    node: {
      fs: "empty",
      module: "empty"
    },
    entry: entryFile,
    output: {
      filename: outputFilename,
      path: buildPath
    },
    resolve: {
      extensions: [".js", ".json", ".vue"]
    },
    module: {
      rules: [
        {
          test: /\.(html)$/,
          use: {
            loader: "html-loader",
            options: {
              attrs: [":data-src"]
            }
          }
        },
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
            isClientServe ? "vue-style-loader" : MiniCssExtractPlugin.loader,
            "css-loader"
          ]
        },
        {
          test: /\.(png|jpg|gif)$/,
          use: ["file-loader"]
        },
        {
          test: /\.(eot|svg|ttf|woff|woff2)$/,
          use: [
            {
              loader: "url-loader",
              options: {
                limit: 8192
              }
            }
          ]
        }
      ]
    },
    devServer: {
      open: false,
      hot: true,
      host: "0.0.0.0",
      port: 8000
    },
    plugins: webpackPlugins
  };

  // 自定义webpack配置
  if (!isClient) {
    webpackConfig.output.globalObject =
      'typeof self !== "undefined" ? self : this';
  }

  return webpackConfig;
};
