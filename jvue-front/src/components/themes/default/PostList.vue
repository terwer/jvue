<template>
  <div id="postList" v-if="postList.length > 0">
    <b-card
      class="pic-text-article"
      tag="article"
      v-for="post in postList"
      :key="post.postId"
      :title="post.postFullTitle === '' ? '无标题' : post.postFullTitle"
      sub-title="发布于5分钟前"
    >
      <b-media style="margin-bottom: 1rem;">
        <b-img
          v-if="!isMobile && post.thumbnails.length > 0"
          slot="aside"
          :src="post.thumbnails[0]"
          width="200"
          height="100"
          :title="post.postFullTitle === '' ? '无标题' : post.postFullTitle"
          :alt="post.postFullTitle === '' ? '无标题' : post.postFullTitle"
        />
        <p class="card-text">
          {{ post.postDesc }}
        </p>
      </b-media>
      <router-link
        :to="
          post.postSlug === ''
            ? '/post/' + post.postId + '.html'
            : '/post/' + post.postSlug + '.html'
        "
      >
        <b-btn type="button" variant="primary">查看全文</b-btn>
      </router-link>
      <div class="article-ext" v-if="!isMobile">
        <span class="article-ext-info">作者：Terwer</span>
        <span class="article-ext-info" :title="post.praiseCount"
          >点赞数：{{ post.praiseCount }}</span
        >
        <span class="article-ext-info" :title="post.viewCount"
          >阅读数：{{ post.viewCount }}</span
        >
        <span class="article-ext-info" :title="post.commentCount"
          >评论数：{{ post.commentCount }}</span
        >
      </div>
    </b-card>
  </div>
</template>

<script>
// import { getLogger } from "../../../util/logger";
// const logger = getLogger("components/themes/default/PosiList");
import { inBrowser } from "../../../util/dom";

export default {
  name: "PostList",
  props: {
    postList: Array
  },
  mounted() {
    // logger.error("this.isMobile=>" + this.isMobile);
  },
  data() {
    return {
      isMobile: inBrowser ? document.body.clientWidth < 768 : false
    };
  }
};
</script>

<style lang="scss">
#postList {
}
#postList .card {
  margin: 10px 0;
}
/**
图片带文字的文章
*/
.pic-text-article {
  .card-subtitle {
    font-size: 14px;
  }
  .article-ext {
    margin-top: 1.25rem;
    .article-ext-info {
      margin-right: 1.25rem;
    }
  }
}
</style>
