<template>
  <div id="postList">
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
            <div class="post-list-title">
              <nuxt-link
                :to="
                  post.name === ''
                    ? '/post/' + post.id + '.html'
                    : '/post/' + post.name + '.html'
                "
              >
                <h2>{{ post.title === "" ? "暂无标题" : post.title }}</h2>
              </nuxt-link>
              <div class="bottom clearfix">
                <div class="page">
                  {{ post.desc === "" ? "暂无简介" : post.desc }}
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="24">
            <div>
              <div class="article-ext">
                <span class="article-ext-info">作者：Terwer</span>
                <span class="article-ext-info" :title="post.viewCount">
                  阅读数：{{ post.hits }}
                </span>
                <span class="article-ext-info" :title="post.commentCount">
                  评论数：{{ post.commentCount }}
                </span>
              </div>
              <div class="time">发布于 {{ post.created }}</div>
              <nuxt-link
                :to="
                  post.name === ''
                    ? '/post/' + post.id + '.html'
                    : '/post/' + post.name + '.html'
                "
              >
                <el-button type="text" class="read-more">查看全文</el-button>
              </nuxt-link>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import { inBrowser } from "../../../util/dom";

export default {
  name: "PostList",
  props: {
    postList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      isMobile: inBrowser ? document.body.clientWidth < 768 : false
    };
  }
};
</script>

<style lang="scss">
.el-card__body {
  padding: 0;
}
.time {
  font-size: 13px;
  color: #999;
  line-height: 10px;
}
.post-item {
  margin: 10px;
  padding: 10px;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.read-more {
  padding: 0;
  margin-top: 15px;
  float: left;
}

.read-more:hover {
  color: #212121;
}

.image {
  width: 100%;
  max-height: 150px;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.page {
  line-height: 30px;
  font-size: 14px;
}
.article-ext {
  font-size: 14px;
  color: #000000;
  .article-ext-info {
    margin-right: 1.25rem;
  }
}
</style>
