const { VueLoaderPlugin } = require("vue-loader");

const config = require("../config");

console.log("process.env.NODE_ENV=>" + process.env.NODE_ENV);
console.log("config.isProduction=>" + config.isProduction);

const webpackCnfig = {
  mode: process.env.NODE_ENV,
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: "vue-loader"
      }
    ]
  },
  plugins: [new VueLoaderPlugin()]
};

// export config
const conf = {
  config: config,
  webpackCnfig: webpackCnfig
};

module.exports = conf;
