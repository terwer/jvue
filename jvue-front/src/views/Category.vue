<template>
  <b-container fluid>
    <HeaderTime />
    <Header />
    <h1 class="text-center">This is category {{ catId }}</h1>
    <Footer :site-config="siteConfigObj" />
    <FriendLink />
  </b-container>
</template>

<script>
import { getLogger } from "../util/logger";
const logger = getLogger("detail");
import { setSession, getSession } from "../util/storage";
const CircularJSON = require("circular-json");
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
import siteConfigApi from "../api/site-config";

export default {
  name: "Category",
  computed: {
    catId() {
      return this.$route.params.id;
    }
  },
  components: {
    HeaderTime,
    Header,
    Footer,
    FriendLink
  },
  data() {
    return {
      siteConfigObj: {}
    };
  },
  created: function() {
    logger.info("Home created,set asyncData");
    const siteConfigData = getSession("siteConfig", "{}");
    this.siteConfigObj = CircularJSON.parse(siteConfigData);
    logger.debug("siteConfigData=>");
    logger.debug(this.siteConfigObj);
  },
  asyncData() {
    // 触发action后，会返回Promise
    logger.info("About page=> asyncData");
    return new Promise((resolve, reject) => {
      const getSiteConfigPromise = siteConfigApi.getSiteConfig();
      Promise.all([getSiteConfigPromise])
        .then(function(values) {
          logger.debug(CircularJSON.stringify(values));
          let asyncDataMap = {};
          const siteConfig = values[0].data;
          if (siteConfig.status === 1) {
            const siteConfigString = CircularJSON.stringify(siteConfig.data);
            asyncDataMap["siteConfig"] = siteConfigString;
            setSession("siteConfig", siteConfigString);
          }
          resolve(asyncDataMap);
        })
        .catch(reason => {
          logger.error("asyncData request error,reason=>" + reason);
          reject(reason);
        });
    });
  }
};
</script>
<style scoped></style>
