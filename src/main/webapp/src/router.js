import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import Index from "./components/themes/default/Index";
import Detail from "./components/themes/default/Detail";
import Category from "./components/themes/default/Category";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "index-short",
    component: Index
  },
  {
    path: "/home",
    name: "index",
    component: Index
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

var router = new VueRouter({
  mode: "history", // process.ssr ? "history" : "hash",
  routes: routes
});
export default router;
