<template>
  <el-container>
    <el-main class="el-container-dark">
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main class="el-main-dark">
            <el-container>
              <el-main class="el-main-dark">
                <el-container>
                  <el-header class="el-header-dark">
                    <HeaderTime />
                  </el-header>
                  <el-header class="el-header-dark">
                    <Header />
                  </el-header>
                  <el-main class="el-main-dark">
                    <Body :type="post" :post-list="postListArray" />
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
  components: { HeaderTime, Header, Body, Footer, FriendLink },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};

    const postsResult = await $axios.$post("/blog/post/list", {
      postType: "post",
      postStatus: "publish",
      pageNum: 1,
      pageSize: 10
    });
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

<style lang="scss">
@import "./common.css";
@import "./dark.css";
</style>
