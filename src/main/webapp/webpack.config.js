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
    const buildPath = argv.buildPath || path.resolve('/dist')
    console.log("buildPath:" + buildPath)
    return {
        // All your other custom config...
        entry: './src/main.js',
        output: {
            filename: '[name].[chunkhash:6].js',
            // path: buildPath
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
            open: false,
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
                        filename: 'common.[chunkhash:6].css'
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