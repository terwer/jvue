/**
 * index
 *
 *@author Terwer
 *@version 1.0
 *2019/3/22 14:46
 **/
import { get, post } from "../plugins/http";
import util from "../util/util";

// 校验相关
const auth = {
  login(user) {
    return post("/admin/auth/login", user);
  },
  logout() {
    return post("/admin/auth/logout");
  },
  getUsername() {
    return get("/admin/auth/username");
  },
  resetPassword(username, oldPassword, newPassword) {
    let params = {
      username: username,
      oldPassword: oldPassword,
      newPassword: newPassword
    };
    return post("/admin/auth/reset", params);
  }
};

// 文章相关
const article = {
  getArticles(params) {
    return post("/admin/post/list", params);
  },
  getArticle(params) {
    return post("/admin/post/" + params.postId);
  },
  getAllCategories() {
    let params = {
      type: util.STATIC.META_CATEGORY
    };
    return post("/admin/meta/list", params);
  },
  getAllTags() {
    let params = {
      type: util.STATIC.META_TAG
    };
    return post("/admin/meta/list", params);
  },
  saveArticle(params) {
    return post("/admin/post/save", params);
  },
  deleteArticle(params) {
    return post("/admin/post/remove", params);
  },
  trashArticle(params) {
    return post("/admin/post/trash", params);
  }
};

export default {
  auth,
  article
};
