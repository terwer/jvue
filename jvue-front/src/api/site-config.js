/**
 * site-config.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-27 下午11:26
 **/
import { sendPost } from "./base";

/**
 * 获取站点配置
 * @returns {AxiosPromise<any>}
 */
const getSiteConfig = () => {
  const GET_SITE_CONFIG = "site/config/list";
  return sendPost(GET_SITE_CONFIG);
};

export default {
  getSiteConfig
};
