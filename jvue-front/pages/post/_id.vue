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
                    <div id="post">
                      <!-- 导航 -->
                      <el-breadcrumb separator="/">
                        <el-breadcrumb-item
                          v-for="item in items"
                          :key="item.text"
                          :to="item.to"
                        >
                          {{ item.text }}
                        </el-breadcrumb-item>
                      </el-breadcrumb>

                      <!-- 文章标题 -->
                      <div id="postTitle">
                        <router-link
                          :to="
                            postObj.name === ''
                              ? '/post/' + postObj.id + '.html'
                              : '/post/' + postObj.name + '.html'
                          "
                        >
                          <h1>
                            {{ postObj.title }}
                          </h1>
                        </router-link>
                      </div>
                    </div>
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
import { getLogger } from "../../util/logger";
import HeaderTime from "../../components/themes/default/HeaderTime";
import Header from "../../components/themes/default/Header";
import Footer from "../../components/themes/default/Footer";
import FriendLink from "../../components/themes/default/FriendLink";

// 全局样式
import "../../components/themes/default/style.css";
const logger = getLogger("pages/post");

export default {
  components: { HeaderTime, Header, Footer, FriendLink },
  data() {
    return {
      id: this.$route.params.id.replace(/\.[^/.]+$/, ""),
      items: [
        {
          text: "首页",
          to: { path: "/" }
        },
        {
          text: "文章"
        }
      ]
    };
  },
  async asyncData(context) {
    const siteConfigResult = await context.$axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};

    const id = context.route.params.id.replace(/\.[^/.]+$/, "");
    const postParams = {
      postSlug: id
    };
    const postResult = await context.$axios.$post(
      "/blog/post/detail",
      postParams
    );
    const postObj = postResult.status === 1 ? postResult.data : {};

    logger.info("fetch siteConfig and post finish");

    return { siteConfigObj, postObj };
  },
  head() {
    return {
      title: "文章" + " - " + this.siteConfigObj.webname,
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

<style scoped>
#post {
  margin: 20px;
}
</style>
