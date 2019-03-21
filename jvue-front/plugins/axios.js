/**
 * axios
 *
 *@author Terwer
 *@version 1.0
 *2019/3/19 16:39
 **/
import qs from "qs";
import { getLogger } from "../util/logger";
const logger = getLogger("plugins/axios");

export default function({ $axios, redirect }) {
  $axios.onRequest(config => {
    const url = config.baseURL + config.url;
    logger.info("url=>" + url);

    const params = config.data;
    logger.info("params=>");
    console.log(params || {});
    config.data = qs.stringify(params);
  });

  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status);
    if (code === 400) {
      console.error("400");
      // redirect("/400");
    } else if (code === 500) {
      console.error("500");
      // redirect("/sorry");
    }
  });
}
