const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
    entry: path.resolve(__dirname, 'src', 'index'),
    plugins: [
        new CleanWebpackPlugin(),
    ],
    module: {
        rules: [
            // Regras para o babel funcionar em todos os arquivos menos no node modules
            {
                test: /\.tsx?$/,
                exclude: /node_modules/,
                loader: 'ts-loader'
            },
            // Images
            {
                test: /\.(?:ico|gif|png|jpg|jpeg)$/i,
                type: 'asset/resource',
            },
            // CSS, PostCSS, and Sass
            {
                test: /\.(scss|css)$/,
                use: ['style-loader', 'css-loader', 'postcss-loader', 'sass-loader'],
            },
            // SVGs
            {
                test: /\.svg$/,
                use: [
                    {
                        loader: 'svg-url-loader',
                        options: {
                            limit: 10000,
                        },
                    },
                ],
            },
        ],
    },

    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
        modules: ['./src', 'node_modules']
    },
}