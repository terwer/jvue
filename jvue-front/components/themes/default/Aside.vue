<template>
  <div id="aside">
    <div v-if="postList.length > 0">
      <el-card v-for="post in postList" :key="post.postId" class="post-item">
        <el-row>
          <el-col
            v-if="!isMobile && post.thumbnails.length > 0"
            :xs="24"
            :md="6"
          >
            <img :src="post.thumbnails[0]" class="image" alt="image" />
          </el-col>
          <el-col :xs="24" :md="post.thumbnails.length > 0 ? 18 : 24">
            <div>
              <nuxt-link
                class="aside-link"
                :to="
                  post.name === '' || post.name === 'null' || post.name === null
                    ? '/post/' + post.id + '.html'
                    : '/post/' + post.name + '.html'
                "
              >
                <h2
                  :class="post.thumbnails.length > 0 ? 'has-image-title' : ''"
                >
                  {{
                    post.title === ""
                      ? "暂无标题"
                      : post.title.length > 36
                      ? post.title.substring(0, 36)
                      : post.title
                  }}
                </h2>
              </nuxt-link>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>
<script>
import { getLogger } from "../../../util/logger";
import { inBrowser } from "../../../util/dom";
const logger = getLogger("components/themes/default/Aside");

export default {
  name: "Aside",
  data() {
    return {
      isMobile: inBrowser ? document.body.clientWidth < 768 : false,
      postList: []
    };
  },
  async mounted() {
    const postsResult = await this.$axios.$post("/blog/post/list", {
      isHot: 1,
      postStatus: "publish",
      postType: "post",
      pageNum: 1,
      pageSize: 15
    });
    this.postList = postsResult.status === 1 ? postsResult.data.list || [] : [];
    logger.info("fetch aside postList finish");
  }
};
</script>

<style scoped>
.has-image-title {
  margin: 0;
}
.image {
  width: 100%;
  max-height: 150px;
  display: block;
  padding: 0 20px 0 0;
  min-height: 75px;
}
</style>
