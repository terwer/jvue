import Vue from "vue";
import Router from "vue-router";
import FrontRoute from "./front";
import AdminRoute from "./admin";

Vue.use(Router);

const routes = FrontRoute.concat(AdminRoute);
console.log("routes=>", routes);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: routes
});
