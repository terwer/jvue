const pkg = require("./package");

// 后端接口地址
const JVUE_SERVER_URL =
  process.env.JVUE_SERVER_URL ?? "http://localhost:8008/api";
const ARTALK_SERVER_URL =
  process.env.ARTALK_SERVER_URL ?? "http://localhost:23366";
console.log("JVUE_SERVER_URL =>", JVUE_SERVER_URL);
console.log("ARTALK_SERVER_URL =>", ARTALK_SERVER_URL);

module.exports = {
  debug: true,
  mode: "universal",
  telemetry: false,
  env: {
    artalkServerUrl: ARTALK_SERVER_URL
  },

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
    link: [
      { rel: "icon", type: "image/x-icon", href: "/favicon.ico" },
      {
        href:
          "https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.3.0/css/lightgallery.css",
        rel: "stylesheet"
      }
    ],
    script: [
      { src: "https://cdnjs.cloudflare.com/ajax/libs/artalk/2.8.3/Artalk.js" }
    ]
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
    baseURL: JVUE_SERVER_URL
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
