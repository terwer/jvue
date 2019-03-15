<template>
  <b-container fluid>
    <HeaderTime />
    <Header />
    <b-row>
      <b-col sm="0" md="0" lg="0" xl="2"></b-col>
      <b-col sm="12" md="12" lg="12" xl="8">
        <div class="text-left">
          <div class="visit">
            谢谢您，您是第
            <img
              src="http://www.cutercounter.com/hit.php?id=gvvnqcod&nd=6&style=47"
              border="0"
              alt="访客数"
            />
            位访客。
          </div>
          <br />
          <h3>网站简介</h3>
          <br />
          <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="font-size: 24px">远</span
            >方的灯塔是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎关注。
          </div>
          <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="font-size: 24px">人</span
            >生在世，有些事情，有些选择，是不需要理由的。年轻就该去探索，去尝试。人生最大的悲哀不是失败，而是甘于现状，因为他一开始就失败了。
          </div>
          <br />
          <h3>作者简历</h3>
          <br />
          <div>
            2014年7月1日毕业于湖北省长江大学软件工程，在CRM客户关系管理、电子商务、互联网金融、互联网K12教育等领域有丰富的工作经验。
          </div>
          <div>
            专注于项目架构、性能优化、算法研究。在Web开发领域有深入研究，精通Java，目前专注于服务端开发。业余时间喜欢探索移动互联网。
          </div>
          <br />
          <div>作者邮箱：youweics@sina.com</div>
        </div>
      </b-col>
      <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    </b-row>
    <Footer :site-config="siteConfigObj" />
    <FriendLink />
  </b-container>
</template>

<script>
import { getLogger } from "../util/logger";
const logger = getLogger("about");
import { setSession, getSession } from "../util/storage";
const CircularJSON = require("circular-json");
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
import siteConfigApi from "../api/site-config";
export default {
  name: "Home",
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

<style>
.visit {
  font-size: 24px;
}
</style>
