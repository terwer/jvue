import Vue from "vue";
import VueRouter from "vue-router";

// 组件引用
import Index from "./views/Index.vue";
import About from "./views/About.vue";
import Detail from "./views/Detail";
import Category from "./views/Category";
import Login from "./views/Login";
import Register from "./views/Register";
import Search from "./views/Search";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "index",
    component: Index
  },
  {
    path: "/home",
    name: "home",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "home" */ "./views/Index.vue")
    component: Index
  },
  {
    path: "/about",
    name: "about",
    component: About
  },
  {
    path: "/auth/login",
    name: "login",
    component: Login
  },
  {
    path: "/auth/register",
    name: "register",
    component: Register
  },
  {
    path: "/s/:k",
    name: "search",
    component: Search
  },
  {
    path: "/post/:id.html",
    name: "detail",
    component: Detail
  },
  {
    path: "/p/:id.html",
    name: "detail-short",
    component: Detail
  },
  {
    path: "/category/:id",
    name: "category",
    component: Category
  },
  {
    path: "/cat/:id",
    name: "category-medium",
    component: Category
  },
  {
    path: "/c/:id",
    name: "category-short",
    component: Category
  }
];

export function createRouter() {
  return new VueRouter({
    mode: "history", // TODO:process.ssr ? "history" : "hash",
    base: process.env.BASE_URL,
    routes: routes
  });
}
