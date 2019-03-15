<template>
  <b-container fluid>
    <HeaderTime />
    <Header />
    <Body
      @refreshList="refreshList"
      :post-list="postListArray"
      :loading-text="loadingText"
    />
    <Footer :site-config="siteConfigObj" />
    <FriendLink />
    <!--<div v-bind:class="showMask ? 'mask' : ''"></div>-->
  </b-container>
</template>

<script>
import { getLogger } from "../util/logger";
const logger = getLogger("views/home");
/**
 * 在 Vue 2.5 以下的版本中，服务端渲染时异步组件只能用在路由组件上。
 * 然而在 2.5+ 的版本中，得益于核心算法的升级，异步组件现在可以在应用中的任何地方使用。
 * https://ssr.vuejs.org/zh/guide/routing.html#代码分割
 */
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Body from "../components/themes/default/Body";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
import siteConfigApi from "../api/site-config";
import postApi from "../api/post";
import { setSession, getSession } from "../util/storage";
const CircularJSON = require("circular-json");

/**
 * 由于没有动态更新，所有的生命周期钩子函数中，
 * 只有 beforeCreate 和 created 会在服务器端渲染 (SSR) 过程中被调用。
 */
export default {
  name: "home",
  components: {
    HeaderTime,
    Header,
    Body,
    Footer,
    FriendLink
  },
  data() {
    return {
      siteConfigObj: {},
      postType: "post",
      currentPage: 1,
      isloadmore: 0,
      loadingText: "加载更多",
      // showMask: false,
      postListArray: []
    };
  },
  computed: {
    k() {
      return this.$route.params.k || "";
    }
  },
  watch: {
    $route(to, from) {
      // to表示的是你要去的那个组件，from 表示的是你从哪个组件过来的，它们是两个对象，你可以把它打印出来，它们也有一个param 属性
      logger.debug("to=>" + to.path);
      logger.debug("from=>" + from.path);
      logger.info("search invoked,key=>" + this.k);
      this.currentPage = 1;
      this.isloadmore = 0;
      this.getSearchResult();
    }
  },
  methods: {
    refreshList(type, isloadmore) {
      logger.info("type=>" + type);
      logger.info("isloadmore=>" + isloadmore);
      if (type !== "") {
        this.postType = type;
      }
      this.isloadmore = isloadmore;
      if (isloadmore === 1) {
        ++this.currentPage;
        logger.info("加载更多");
      } else {
        this.currentPage = 1;
        logger.info("切换类型");
      }
      // 获取数据
      this.getSearchResult();
      logger.info(
        "refreshList invoked,type=>" +
          this.postType +
          ",isloadmore=>" +
          this.isloadmore +
          ",currentPage=>" +
          this.currentPage
      );
    },
    getSearchResult: function() {
      let that = this;
      that.$Progress.start();
      // that.showMask = true;
      if (that.currentPage > 1) {
        that.loadingText = "加载中...";
      }
      postApi
        .getPostList({
          search: that.k,
          postType: that.postType,
          postStatus: "publish",
          page: that.currentPage
        })
        .then(resolve => {
          that.$Progress.finish();
          // that.showMask = false;
          const postList = resolve.data;
          if (postList.code === 0) {
            // console.log(postList.data);
            // 填充数据
            if (that.isloadmore === 1) {
              // 加载更多，追加
              if (postList.data.length === 0) {
                that.loadingText = "加载完成";
                that.$toaster.warning("暂无结果");
              } else {
                that.loadingText = "加载更多";
              }
              for (const idx in postList.data) {
                const post = postList.data[idx];
                that.postListArray.push(post);
              }
            } else {
              // 切换，需要重置
              that.postListArray = postList.data;
            }
          } else {
            that.loadingText = "加载失败";
            that.$toaster.error(postList.msg);
          }
        })
        .catch(reason => {
          that.loadingText = "网络异常";
          that.$Progress.finish();
          that.showMask = false;
          logger.error("getSearchResult request error,reason=>" + reason);
          that.$toaster.error(reason);
        });
    }
  },
  created: function() {
    logger.info("Home created,set asyncData");
    const siteConfigData = getSession("siteConfig", "{}");
    this.siteConfigObj = CircularJSON.parse(siteConfigData);
    logger.debug("this.siteConfigObj=>");
    logger.debug(typeof this.siteConfigObj);
    logger.debug(this.siteConfigObj);

    const postListData = getSession("postList", "[]");
    this.postListArray = CircularJSON.parse(postListData);
    logger.debug("this.postListArray=>");
    logger.debug(typeof this.postListArray);
    logger.debug(this.postListArray);
  },
  asyncData() {
    // 触发action后，会返回Promise
    logger.info("Home page=> asyncData");
    return new Promise((resolve, reject) => {
      const getSiteConfigPromise = siteConfigApi.getSiteConfig();
      const getPostListPromise = postApi.getPostList({
        postType: "post",
        postStatus: "publish",
        page: 1
      });
      Promise.all([getSiteConfigPromise, getPostListPromise])
        .then(function(values) {
          logger.debug(CircularJSON.stringify(values));
          let asyncDataMap = {};
          const siteConfig = values[0].data;
          const postList = values[1].data;
          if (siteConfig.status === 1) {
            const siteConfigString = CircularJSON.stringify(siteConfig.data);
            asyncDataMap["siteConfig"] = siteConfigString;
            setSession("siteConfig", siteConfigString);
          }
          if (postList.code === 0) {
            const postListString = CircularJSON.stringify(postList.data);
            asyncDataMap["postList"] = postListString;
            setSession("postList", postListString);
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
