/**
 * post.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-27 下午11:26
 **/
import { sendPost } from "./base";

/**
 * 获取文章列表
 * @param params 参数
 * @returns {AxiosPromise<any>}
 */
const getPostList = params => {
  const GET_POST_LIST = "blog/post/list";
  return sendPost(GET_POST_LIST, params);
};

/**
 * 获取热门文章列表
 * @param params 参数
 * @returns {AxiosPromise<any>}
 */
const getHotPostList = params => {
  const GET_HOT_POST_LIST = "blog/post/hot";
  return sendPost(GET_HOT_POST_LIST, params);
};

/**
 * 获取文章详情
 * @param postSlug 文章ID或者文章别名
 * @returns {AxiosPromise<any>}
 */
const getPost = postSlug => {
  const GET_POST = "blog/post/" + postSlug;
  return sendPost(GET_POST);
};

export default {
  getPostList,
  getHotPostList,
  getPost
};
