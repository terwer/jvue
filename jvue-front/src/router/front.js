/**
 * front
 *
 *@author Terwer
 *@version 1.0
 *2019/3/4 12:43
 **/
import Home from "../views/Home";
import About from "../views/About";
import Detail from "../views/Detail";
import Login from "../views/Login";
import Register from "../views/Register";
import Category from "../views/Category";

export default [
  {
    path: "/",
    name: "home",
    component: Home
    // component: () =>
    //   import(/* webpackChunkName: "homepage" */ "./views/Home.vue")
  },
  {
    path: "/home",
    name: "home-long",
    component: Home
  },
  {
    path: "/s",
    name: "search",
    component: Home
  },
  {
    path: "/s/:k",
    name: "search",
    component: Home
  },
  {
    path: "/about",
    name: "about",
    component: About
    // route level code-splitting
    // this generates a separate chunk (aboutpage.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () =>
    //   import(/* webpackChunkName: "aboutpage" */ "./views/About.vue")
  },
  {
    path: "/post/:id.html",
    name: "detail",
    component: Detail
  },
  {
    path: "/post/:id",
    name: "detail-no-ext",
    component: Detail
  },
  {
    path: "/p/:id.html",
    name: "detail-short",
    component: Detail
  },
  {
    path: "/p/:id",
    name: "detail-short-no-ext",
    component: Detail
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
