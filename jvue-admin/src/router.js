import Vue from "vue";
import Router from "vue-router";
import Admin from "./views/Admin.vue";
import Login from "./views/Login.vue";
import Dashboard from "./components/Dashboard";

import PostList from "./components/post/PostList";
import PostEdit from "./components/post/PostEdit";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "admin",
      component: Admin,
      redirect: "/dashboard",
      children: [
        {
          path: "dashboard",
          component: Dashboard
        },
        {
          path: "post/publish/:id",
          component: PostEdit
        },
        {
          path: "post/publish",
          component: PostEdit
        },
        {
          path: "post",
          component: PostList
        }
        // {
        //   path: "comment",
        //   component: CommentList
        // },
        // {
        //   path: "tag",
        //   component: TagList
        // },
        // {
        //   path: "page",
        //   component: PageList
        // },
        // {
        //   path: "page/publish/:id",
        //   component: PageEdit
        // },
        // {
        //   path: "page/publish",
        //   component: PageEdit
        // },
        // {
        //   path: "setting",
        //   component: Setting
        // }
      ]
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    }
  ]
});
