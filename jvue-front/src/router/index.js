import Vue from "vue";
import Router from "vue-router";
import FrontRoutes from "./front";

Vue.use(Router);

console.log("routes=>", FrontRoutes);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: FrontRoutes
});
