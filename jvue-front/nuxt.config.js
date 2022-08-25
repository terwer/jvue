const pkg = require("./package");

const development = process.env.NODE_ENV !== "production";

module.exports = {
  debug: true,
  mode: "universal",

  /*
   ** Headers of the page
   */
  head: {
    title: pkg.name,
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      { hid: "description", name: "description", content: pkg.description }
    ],
    link: [{ rel: "icon", type: "image/x-icon", href: "/favicon.ico" }]
  },

  /*
   ** Customize the progress-bar color
   */
  loading: { color: "#fff" },

  /*
   ** Global CSS
   */
  css: [],

  /*
   ** Plugins to load before mounting the App
   */
  plugins: [
    "~/plugins/axios",
    "~plugins/element-ui",
    {
      src: "@/plugins/font-awesome",
      ssr: false // 只在客户端打包
    },
    // {
    //   src: "~/plugins/vue-uweb",
    //   ssr: false // 只在客户端打包
    // },
    {
      src: "~/plugins/vue-hljs",
      ssr: false // 只在客户端打包
    },
    {
      src: "~/plugins/baidu-tongji",
      ssr: false // 只在客户端打包
    }
  ],

  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    "@nuxtjs/axios"
  ],
  /*
   ** Axios module configuration
   */
  axios: {
    // See https://github.com/nuxt-community/axios-module#options
    baseURL: development
      ? "http://localhost:8002/api"
      : "https://v4.terwergreen.com:8002/api"
  },

  /*
   ** Build configuration
   */
  build: {
    transpile: [/^element-ui/],

    /*
     ** You can extend webpack config here
     */
    extend(config, ctx) {
      // Run ESLint on save
      if (ctx.isDev && ctx.isClient) {
        config.module.rules.push({
          enforce: "pre",
          test: /\.(js|vue)$/,
          loader: "eslint-loader",
          exclude: /(node_modules)/
        });
      }
    }
  }
};
