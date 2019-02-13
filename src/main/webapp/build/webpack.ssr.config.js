module.exports = {
  mode: process.env.NODE_ENV,
  node: {
    fs: "empty",
    module: "empty"
  },
  entry: "./server.js",
  output: {
    filename: "server-bundle.js"
  }
};
