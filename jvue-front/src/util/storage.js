/**
 * storage.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-28 上午12:22
 **/
import { getLogger } from "./logger";
const logger = getLogger("util/storage");
const CircularJSON = require("circular-json");
import { isEmptyOrUndefined } from "./string";
import { inBrowser } from "../util/dom";
import Vue from "vue";

/**
 * 设置Session缓存
 * @param key key
 * @param value value
 */
const setSessionStorage = (key, value) => {
  console.log("Vue.$sessionStorage.set=>key:", key);
  console.log("Vue.$sessionStorage.set=>value:", value);
  Vue.$sessionStorage.set(key, value);
};

/**
 * 获取Session缓存
 * @param key key
 * @returns {*}
 */
// const getSessionStorage = key => {
//   return getSessionStorageOrDefault(key, "");
// };

/**
 * 获取Session缓存带默认值
 * @param key
 * @param val
 * @returns string
 */
const getSessionStorageOrDefault = (key, val) => {
  const value = Vue.$sessionStorage.get(key);
  console.log("Vue.$sessionStorage.get=>key:", key);
  // console.log("Vue.$sessionStorage.get=>value:", value);
  return isEmptyOrUndefined(value) ? val : value;
};

const setSession = (key, value) => {
  logger.debug("process.env.SSR_ENV=>", process.env.SSR_ENV);
  if (inBrowser) {
    // 客户端存储数据
    logger.debug("setSession to $sessionStorage");
    setSessionStorage(key, value);
  } else {
    // 服务端设置Session
    logger.debug("setSession to server");
    global.setSessionCallback(key, value);
  }
};

const getSession = (key, val) => {
  let data = "";
  if (inBrowser) {
    // 客户端获取数据
    // 优先获取SessionStorage，因为这里是最新更新的数据，点击客户端路由就会更新
    data = getSessionStorageOrDefault(key, val);
    logger.debug("get data from sessionStorage");
    // logger.debug(data);
    // 没有Session取window.__INITIAL_STATE__，这里只有触发服务端渲染才会更新
    logger.debug("storage window.__INITIAL_STATE__=>");
    logger.debug(window.__INITIAL_STATE__);
    // 如果Session空，就从window.__INITIAL_STATE__获取值
    const isEmpty = data === "[]" || data === "{}";
    if (isEmpty && !isEmptyOrUndefined(window.__INITIAL_STATE__)) {
      logger.debug("get data from window.__INITIAL_STATE__");
      const initDataMap = window.__INITIAL_STATE__[0];
      const curStr = CircularJSON.parse(initDataMap[key]);
      console.log(typeof curStr);
      data = CircularJSON.stringify(curStr);
    }
  } else {
    // 服务端获取Session
    data = global.getSessionCallback(key);
    logger.debug("getSession from server");
  }
  logger.debug("getSession data success");
  return data;
};

export { setSession, getSession };
