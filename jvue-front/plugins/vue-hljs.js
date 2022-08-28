/**
 * vue-hljs
 *
 *@author Terwer
 *@version 1.0
 *2019/3/21 12:02
 **/
import Vue from "vue";
import { getLogger } from "../util/logger";
import vueHljs from "./lib/vue-hljs/main";
const logger = getLogger("plugins/vue-hljs");

Vue.use(vueHljs);
logger.debug("Register vue-hljs success");
