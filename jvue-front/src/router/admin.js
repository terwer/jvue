/**
 * admin
 *
 *@author Terwer
 *@version 1.0
 *2019/3/4 12:44
 **/
import AdminLogin from "../views/admin/AdminLogin";
const ADMIN_PATH = process.env.VUE_APP_ADMIN_PATH;

export default [
  {
    path: ADMIN_PATH,
    redirect: ADMIN_PATH + "/dashboard"
  },
  {
    path: ADMIN_PATH,
    component: resolve => require(["../views/admin/AdminHome.vue"], resolve),
    meta: { title: "系统首页" },
    children: [
      {
        path: ADMIN_PATH + "/dashboard",
        component: resolve =>
          require(["../views/admin/Dashboard.vue"], resolve),
        meta: { title: "系统首页" }
      },
      {
        path: ADMIN_PATH + "/post/manage/list",
        component: resolve =>
          require(["../views/admin/postManage/PostList.vue"], resolve),
        meta: { title: "文章" }
      },
      {
        path: ADMIN_PATH + "/post/manage/edit",
        component: resolve =>
          require(["../views/admin/postManage/PostEdit.vue"], resolve),
        meta: { title: "文章" }
      }
    ]
  },
  {
    path: ADMIN_PATH + "/login",
    name: "admin/login",
    component: AdminLogin
  }
];
