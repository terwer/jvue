<template>
  <el-container>
    <el-main class="el-container">
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main class="el-main">
            <el-container>
              <el-main class="el-main">
                <el-container>
                  <el-header class="el-header">
                    <HeaderTime />
                  </el-header>
                  <el-header class="el-header">
                    <Header />
                  </el-header>
                  <el-main class="el-main">
                    <Body type="note" :post-list="postListArray" />
                  </el-main>
                </el-container>
              </el-main>
            </el-container>
          </el-main>
        </el-col>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
      </el-row>
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-footer>
            <FriendLink />
            <Footer :site-config="siteConfigObj" />
          </el-footer>
        </el-col>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Body from "../components/themes/default/Body";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";

export default {
  components: { HeaderTime, Header, Body, Footer, FriendLink },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};

    const postsResult = await $axios.$post("/blog/post/list", {
      postType: "note",
      postStatus: "publish",
      pageNum: 1,
      pageSize: 10
    });
    const postListArray = postsResult.status === 1 ? postsResult.data.list : [];

    return { siteConfigObj, postListArray };
  },
  data() {
    return {
      postListArray: []
    };
  },
  head() {
    return {
      title: "学习笔记" + " - " + this.siteConfigObj.webname,
      meta: [
        {
          name: "keywords",
          content: "归档,笔记,总结,大纲"
        },
        {
          hid: "description",
          name: "description",
          content:
            "这里是归档和总结各种学习笔记的地方。内容为更加条理化、系统化。"
        }
      ]
    };
  }
};
</script>

<style lang="scss">
@import "./common.css";
@import "./default.css";
</style>
