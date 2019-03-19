<template>
  <el-container>
    <p>postListArray=>{{ postListArray }}</p>
    <p>siteConfigObj=>{{ siteConfigObj }}</p>
  </el-container>
</template>

<script>
export default {
  name: "Index",
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const postsResult = await $axios.$post("/blog/post/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};
    const postListArray = postsResult.status === 1 ? postsResult.data.list : [];

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

<style scoped></style>
