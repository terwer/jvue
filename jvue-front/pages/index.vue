<template>
  <el-container>
    <el-header>Header</el-header>
    <el-main>Main</el-main>
    <!--
    <p>postListArray=>{{ postListArray }}</p>
    <p>siteConfigObj=>{{ siteConfigObj }}</p>
    -->
    <el-footer>
      <Footer />
    </el-footer>
  </el-container>
</template>

<script>
import { getLogger } from "../util/logger";
import Footer from "../components/themes/default/Footer";
const logger = getLogger("pages/index");

export default {
  name: "Index",
  components: { Footer },
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

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
body > .el-container {
  margin-bottom: 40px;
}
</style>
