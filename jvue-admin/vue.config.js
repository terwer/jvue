module.exports = {
    baseUrl: '/admin/',
    // https://cli.vuejs.org/zh/config/#configurewebpack
    configureWebpack: config => {
        // 解决Invalid Host header
        config.devServer = {
            disableHostCheck: true
        };
    }
}