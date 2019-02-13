const config = require("./config");

module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: [
    "google",
    "eslint:recommended",
    "plugin:vue/essential",
    "@vue/prettier"
  ],
  // required to lint *.vue files
  plugins: ["html"],
  settings: {
    "import/resolver": {
      webpack: {
        config: "build/webpack.base.conf.js"
      }
    }
  },
  // add your custom rules here
  rules: {
    "no-console": "off",

    // allow debugger; instruction during development
    "no-debugger": config.isProduction ? 2 : 0,

    "no-unused-vars": [
      2,
      {
        vars: "local",
        args: "none"
      }
    ],
    semi: ["error", "always"],

    // don"t require comma in the last line of an object/dictionary declaration
    "comma-dangle": ["error", "never"],

    // force space after and before curly braces in object/dict declarations
    "object-curly-spacing": ["error", "always"],

    // ignore max-len for comments
    "max-len": [
      "error",
      {
        code: 100,
        ignoreComments: true,
        ignoreTrailingComments: true,
        ignoreUrls: true,
        ignoreStrings: true
      }
    ],

    // force "===" in comparisons when ambiguous
    eqeqeq: ["error", "smart"],

    // force double quotes
    quotes: ["error", "double"],

    "require-jsdoc": 1,

    "new-cap": ["error", { capIsNew: false }]
  },
  parserOptions: {
    sourceType: "module",
    parser: "babel-eslint"
  }
};
