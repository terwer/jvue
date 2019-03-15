module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: ["plugin:vue/essential", "@vue/prettier"],
  rules: {
    "no-undef": "off",
    "no-console": "off", //process.env.NODE_ENV === "production" ? "error" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-unused-vars": [
      2,
      {
        vars: "local",
        args: "none"
      }
    ],
    semi: ["error", "always"]
  },
  parserOptions: {
    parser: "babel-eslint"
  }
};
