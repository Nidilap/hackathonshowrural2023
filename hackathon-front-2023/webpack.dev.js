const path = require('path');
const { HotModuleReplacementPlugin } = require('webpack');

const { merge } = require("webpack-merge");
const common = require("./webpack.common.js");

module.exports = merge(common, {
    mode: 'development',
    devServer: {
        static: {
            directory: path.join(__dirname, './dist'),
        },
        compress: true,
        port: 9000,
    },

    plugins: [
        // Atualize apenas o que mudou no hot reload
        new HotModuleReplacementPlugin(),
    ]
});