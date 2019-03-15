module.exports = {
    // https://cli.vuejs.org/zh/config/#configurewebpack
    configureWebpack: config => {
        // 解决Invalid Host header
        config.devServer.set('disableHostCheck', true);
    }
}