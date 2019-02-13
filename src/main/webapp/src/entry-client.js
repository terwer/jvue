import { createApp } from "./app";
const config = require("../config");

// get context
const context = Object.assign({ url: "/" }, config.seo);

const { app } = createApp(context);

// actually mount to DOM
app.$mount("#app");
