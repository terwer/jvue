module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: ["eslint:recommended", "plugin:vue/essential", "@vue/prettier"],
  rules: {
    "no-console": "off",
    "no-debugger": "off",
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
