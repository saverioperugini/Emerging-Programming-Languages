var path = require("path");

module.exports = {
    mode: "development",
    entry: "./src/App.fsx",
    output: {
        path: path.join(__dirname, "./public"),
        filename: "bundle.js",
        chunkFilename: "[name].bundle.js"
    },
    optimization: {
        splitChunks: {
            chunks: "async"
        }
    },
    devServer: {
        contentBase: path.join(__dirname, "./public"),
        port: 8080,
		historyApiFallback: true
    },
    module: {
        rules: [{
            test: /\.fs(x|proj)?$/,
            use: "fable-loader"
        }]
    }
}