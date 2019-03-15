<template>
  <b-container fluid>
    <HeaderTime />
    <Header />
    <b-row>
      <b-col sm="0" md="0" lg="0" xl="2"></b-col>
      <b-col>
        <b-breadcrumb :items="items" />
        <div id="postTitle">
          <router-link
            :to="
              postObj.postSlug === ''
                ? '/post/' + postObj.postId + '.html'
                : '/post/' + postObj.postSlug + '.html'
            "
          >
            <h1>
              {{ postObj.postFullTitle }}
            </h1>
          </router-link>
        </div>
        <div id="menuTree" style="display: none;"></div>
        <div class="clear"></div>
        <div id="postContent" v-html="postObj.postContent" v-highlight></div>
        <div class="text-center">
          <span>本文为原创内容，作者：Terwer，转载请注明出处！</span>
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
const logger = getLogger("detail");
import { setSession, getSession } from "../util/storage";
const CircularJSON = require("circular-json");
import { inBrowser } from "../util/dom";
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
import siteConfigApi from "../api/site-config";
import postApi from "../api/post";

export default {
  name: "Detail",
  computed: {
    postId() {
      return this.$route.params.id;
    }
  },
  watch: {
    $route(to, from) {
      // to表示的是你要去的那个组件，from 表示的是你从哪个组件过来的，它们是两个对象，你可以把它打印出来，它们也有一个param 属性
      console.log(to);
      console.log(from);
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
      items: [
        {
          text: "首页",
          to: "/"
        },
        {
          text: "文章",
          active: true
        }
      ],
      siteConfigObj: {},
      postObj: {}
    };
  },
  created: function() {
    logger.info("Home created,set asyncData");
    const siteConfigData = getSession("siteConfig", "{}");
    this.siteConfigObj = CircularJSON.parse(siteConfigData);
    logger.debug("siteConfigData=>");
    logger.debug(this.siteConfigObj);

    // 缓存获取文章
    const postData = getSession("post", "{}");
    this.postObj = CircularJSON.parse(postData);
    logger.debug("postData=>");
    logger.debug(this.postObj);

    if (inBrowser) {
      // 高亮数学公式
      MathJax.Hub.Config({
        tex2jax: {
          inlineMath: [["$", "$"], ["\\(", "\\)"]]
        }
      });

      this.$nextTick(function() {
        MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
      });
      logger.info("MathJax hilight success");
    }
  },
  asyncData(route) {
    // 触发action后，会返回Promise
    logger.info("About page=> asyncData,params=>");
    logger.info(route.to.params);
    return new Promise((resolve, reject) => {
      const getSiteConfigPromise = siteConfigApi.getSiteConfig();
      const postDetailPromise = postApi.getPost(route.to.params.id);
      Promise.all([getSiteConfigPromise, postDetailPromise])
        .then(function(values) {
          logger.debug(CircularJSON.stringify(values));
          let asyncDataMap = {};
          // 站点配置
          const siteConfig = values[0].data;
          if (siteConfig.status === 1) {
            const siteConfigString = CircularJSON.stringify(siteConfig.data);
            asyncDataMap["siteConfig"] = siteConfigString;
            setSession("siteConfig", siteConfigString);
          }

          // 文章详情
          const post = values[1].data;
          if (post.status === 1) {
            const postString = CircularJSON.stringify(post.data);
            asyncDataMap["post"] = postString;
            setSession("post", postString);
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

<style lang="scss">
.breadcrumb {
  margin-top: 10px;
}

#postTitle {
  a {
    color: #000;
    line-height: 1.5;
    text-decoration: none;
  }
  a:hover {
    color: red;
  }
  h1 {
    border-bottom: 1px solid #ddd;
    font-size: 14px;
    font-weight: bold;
    margin: 20px 0 10px;
    padding-bottom: 5px;
  }
}

#postContent {
  h1 {
    font-size: 28px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h2 {
    font-size: 21px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  p {
    // 图片自适应
    img {
      max-width: 100% !important;
    }
  }
}

/*生成博客目录的CSS*/
#menuTree {
  float: left;
  min-height: 20px;
  padding: 0 15px 5px 0;
  min-width: 200px;
  margin-bottom: 10px;
  background-color: #fbfbfb;
  border: 1px solid #999;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
  -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
}
#menuTree li {
  list-style-type: none;
  margin-left: 10px;
  padding: 5px 5px 0 15px;
  position: relative;
  width: auto;
}
#menuTree li::before {
  content: "";
  left: 0;
  top: 0;
  position: absolute;
  border-left: 1px solid #999;
  height: 100%;
}
#menuTree li > i {
  font-style: normal;
  cursor: pointer;
  margin-left: 5px;
}
#menuTree > li::before {
  border: 0;
}
#menuTree > li:first-child > i {
  float: right;
}
</style>
