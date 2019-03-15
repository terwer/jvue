/**
 * base.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-27 下午11:19
 **/
import { getLogger } from "../util/logger";
const logger = getLogger("api/post");
const axios = require("axios");

/**
 * 创建http请求对象
 * @returns {AxiosInstance}
 */
const getHttp = () => {
  logger.info("创建http请求对象");
  logger.debug(`process.env.NODE_ENV=>${process.env.NODE_ENV}`);
  // 配置api请求链接
  const baseUrl =
    process.env.NODE_ENV === "production"
      ? "http://www.terwergreen.com/api/"
      : "http://localhost:8081/api/";
  logger.info(`baseUrl=>${baseUrl}`);

  return axios.create({
    baseURL: baseUrl,
    timeout: 10000
  });
};

/**
 * 发送post请求
 * @param url 链接
 * @param payloadParms 参数
 * @returns {AxiosPromise<any>}
 */
export const sendPost = (url, payloadParms) => {
  const http = getHttp();

  // 把Payload参数转换为http参数
  const params = new URLSearchParams(payloadParms);
  logger.info("url=>" + url);
  logger.info("params=>");
  console.log(params);

  return http.post(url);
};
