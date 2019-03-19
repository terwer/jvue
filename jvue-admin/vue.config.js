/**
 * vue.config
 *
 *@author Terwer
 *@version 1.0
 *2019/3/19 13:03
 **/
module.exports = {
  publicPath: "/admin/",
  // https://cli.vuejs.org/zh/config/#configurewebpack
  chainWebpack: config => {
    // 解决Invalid Host header
    config.devServer.set("disableHostCheck", true);
  }
};
