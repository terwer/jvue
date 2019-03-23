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
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Body from "../components/themes/default/Body";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
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
@import "./common.css";
@import "./default.css";
</style>
