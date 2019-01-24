module.exports = {
  presets: ["@babel/preset-env"],
  plugins: [
    "transform-vue-jsx",
    "@babel/plugin-transform-runtime",
    [
      "component",
      {
        libraryName: "element-ui",
        styleLibraryName: "theme-chalk"
      }
    ]
  ]
};
