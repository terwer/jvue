/**
 * index
 *
 *@author Terwer
 *@version 1.0
 *2019/3/22 14:46
 **/
import { get, post } from "../plugins/http";

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
    return post("/blog/post/list", params);
  }
};

export default {
  auth,
  article
};
