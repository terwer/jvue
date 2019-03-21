/**
 * vue-hljs
 *
 *@author Terwer
 *@version 1.0
 *2019/3/21 12:02
 **/
import Vue from "vue";
import vueHljs from "vue-hljs";
import { getLogger } from "../util/logger";
import("./lib/vue-hljs/vs.css");
const logger = getLogger("plugins/vue-hljs");

Vue.use(vueHljs);
logger.debug("Register vue-hljs success");
