<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main>
            <el-container>
              <el-main>
                <el-container>
                  <el-header>
                    <HeaderTime />
                  </el-header>
                  <el-header>
                    <Header />
                  </el-header>
                  <el-main>
                    <Body :post-list="postListArray" />
                  </el-main>
                </el-container>
              </el-main>
            </el-container>
          </el-main>
        </el-col>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-footer>
            <Footer :site-config="siteConfigObj" />
            <FriendLink />
          </el-footer>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import { getLogger } from "../util/logger";
import HeaderTime from "../components/themes/dark/HeaderTime";
import Header from "../components/themes/dark/Header";
import Body from "../components/themes/dark/Body";
import Footer from "../components/themes/dark/Footer";
import FriendLink from "../components/themes/dark/FriendLink";
const logger = getLogger("pages/index");

export default {
  name: "Index",
  components: { HeaderTime, Header, Body, Footer, FriendLink },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const postsResult = await $axios.$post("/blog/post/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};
    const postListArray = postsResult.status === 1 ? postsResult.data.list : [];
    logger.info("fetch siteConfig and postList finish");

    return { siteConfigObj, postListArray };
  },
  head() {
    return {
      title: this.siteConfigObj.webname + " - " + this.siteConfigObj.webslogen,
      meta: [
        {
          name: "keywords",
          content: this.siteConfigObj.keywords
        },
        {
          hid: "description",
          name: "description",
          content: this.siteConfigObj.description
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
/*!
 * Theme Name: dark
 * Theme URL:  http://www.terwergreen.com
 * Description: 本主题是一款适合博客、新闻资讯、自媒体的主题模板。基于bootstrap响应式布局，自适应PC、手机、平板等多种设备浏览。包含普通文章、专题、视频、图集、下载五中文章类型，多种文章页模板。自带前端用户中心，前端登录注册、资料编辑、投稿、文章编辑、评论管理、收藏管理、用户关注一应俱全。文章列表及评论列表AJAX加载。视频支持本地上传、视频链接及视频平台视频。
 * Author: 倚楼听雨
 * Author URI: http://www.terwergreen.com
 * Tags:blog
 * Version: 1.0
 */
body {
  font-family: "Microsoft Yahei", Helvetica, Arial, Verdana, Tahoma, sans-serif !important;
  color: #666 !important;
}
body,
* {
  box-sizing: border-box;
}
body a {
  text-decoration: none;
  color: #00a4ff;
}
body a:hover {
  color: orangered;
}
.el-header,
.el-footer {
  background-color: #212121;
  color: #333;
  line-height: 60px;
  padding: 0;
  height: auto !important;
}
.el-main {
  background-color: #181818;
  color: #333;
  text-align: left;
  line-height: 45px;
  padding: 0;
  overflow: hidden;
}
body > .el-container {
  margin-bottom: 40px;
}
.hide {
  display: none;
}
/*!
* 遮罩层
*/
.mask {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: 999999;
}
</style>
