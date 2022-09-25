module.exports = {
  root: true,
  env: {
    browser: true,
    node: true
  },
  parserOptions: {
    parser: "babel-eslint"
  },
  extends: [
    "@nuxtjs",
    "plugin:nuxt/recommended",
    "plugin:prettier/recommended",
    "prettier",
    "prettier/vue"
  ],
  plugins: ["prettier"],
  // add your custom rules here
  rules: {
    "nuxt/no-cjs-in-config": "off",
    "no-console": "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-unused-vars": [
      2,
      {
        vars: "local",
        args: "none"
      }
    ]
  }
};
