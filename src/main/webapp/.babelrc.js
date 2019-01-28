module.exports = {
  presets: ["@babel/preset-env"],
  plugins: [
    "transform-vue-jsx",
    "@babel/plugin-transform-runtime",
    "@babel/plugin-syntax-dynamic-import",
    "@babel/plugin-transform-async-to-generator"
  ]
};
