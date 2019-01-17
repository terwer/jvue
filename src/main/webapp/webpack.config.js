const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

module.exports = (env, argv) => {
    // CSS提取应该只用于生产环境
    // 这样我们在开发过程中仍然可以热重载
    console.log("mode:" + argv.mode)
    const isProduction = argv.mode === 'production';
    return {
        // All your other custom config...
        entry: './src/main.js',
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
                        filename: 'common.[chunkhash].css'
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