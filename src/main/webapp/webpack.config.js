const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const path = require('path')

module.exports = (env, argv) => {
    // CSS提取应该只用于生产环境
    // 这样我们在开发过程中仍然可以热重载
    const isProduction = argv.mode === 'production';
    console.log("mode:" + argv.mode)
    const renderMode = argv.renderMode ? argv.renderMode : 'client'
    console.log("renderMode:" + renderMode)
    const buildPath = renderMode === 'client'
        ? path.resolve('dist') :
        (renderMode === 'server:client'
                ? path.resolve('ssrclientdist')
                : path.resolve('ssrdist')
        )
    console.log("buildPath:" + buildPath)
    const entryFile = renderMode === 'client'
        ? './src/main.js' :
        (renderMode === 'server:client'
            ? './ssr/client.js'
            : './ssr/server.js')
    console.log("entryFile:" + entryFile)
    return {
        // All your other custom config...
        node: {
            fs: "empty",
            module: "empty"
        },
        entry: entryFile,
        output: {
            filename: renderMode === 'client'
                ? '[name].[hash:6].js' :
                (renderMode === 'server:client'
                    ? 'client.js' :
                    'server.js'),
            path: buildPath
        },
        module: {
            rules: [
                {
                    test: /\.vue$/,
                    exclude: /node_modules/,
                    loader: 'vue-loader'
                },
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: {
                        loader: 'babel-loader'
                    }
                },
                {
                    test: /\.css$/,
                    use: [
                        !isProduction
                            ? 'vue-style-loader'
                            : MiniCssExtractPlugin.loader,
                        'css-loader'
                    ]
                }
            ]
        },
        devServer: {
            open: true,
            hot: true,
            port: 8000
        },
        plugins:
            isProduction
                ? [
                    new VueLoaderPlugin(),
                    new HtmlWebpackPlugin({
                        template: './src/index.html',
                    }),
                    // CSS剥离
                    new MiniCssExtractPlugin({
                        filename: renderMode === 'client' ? 'common.[hash:6].css' : 'common.css'
                    })
                ]
                : [
                    new VueLoaderPlugin(),
                    new HtmlWebpackPlugin({
                        template: './src/index.html',
                    }),
                    // 热加载
                    new webpack.HotModuleReplacementPlugin()
                ]
    }
}