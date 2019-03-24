<template>
  <div>
    <el-row :gutter="50">
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <div class="panel">
          <div class="panel-content">
            <div class="message">
              <h3>{{ articleCount }}</h3>
              <p>发布文章数</p>
            </div>
            <div class="icon">
              <span class="icon-book"></span>
            </div>
            <div style="clear: both"></div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <div class="panel">
          <div class="panel-content">
            <div class="message">
              <h3>{{ commentCount }}</h3>
              <p>评论数</p>
            </div>
            <div class="icon red">
              <span class="icon-comment-o"></span>
            </div>
            <div style="clear: both"></div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="50">
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <div class="panel">
          <div class="panel-content">
            <div class="header">
              <div class="title">最新文章</div>
            </div>
            <ul class="info">
              <li v-for="article in articles" :key="article.id">
                <a
                  target="_blank"
                  :href="
                    frontUrl +
                      'post-dark/' +
                      (article.name === '' ? article.id : article.name) +
                      '.html'
                  "
                >
                  {{ article.title }}
                </a>
              </li>
            </ul>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <div class="panel">
          <div class="panel-content">
            <div class="header">
              <div class="title">最新评论</div>
            </div>
            <ul class="info">
              <li v-for="comment in comments" :key="comment.id">
                {{ comment.name }} => {{ comment.content }}
              </li>
            </ul>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import serverConfig from "../../server-config";

export default {
  data() {
    return {
      frontUrl: serverConfig.frontUrl,
      commentCount: 0,
      articleCount: 0,
      comments: [],
      articles: []
    };
  },
  methods: {
    getCommentCount(data) {
      this.commentCount = data.data.list.length;
    },
    getArticleCount(data) {
      this.articleCount = data.data.list.length;
    },
    getComments(data) {
      for (let key in data.data.list) {
        let comment = data.data.list[key];
        if (comment.content.length > 200) {
          comment.content = comment.content.substring(0, 80) + "...";
        }
        this.comments.push(comment);
      }
    },
    getArticle(data) {
      this.articles = data.data.list;
    },
    initData(articlesData, logsData, commentsData) {
      this.getArticleCount(articlesData);
      this.getArticle(articlesData);
      // this.getComments(commentsData);
      // this.getCommentCount(commentsData);
    },
    init() {
      this.$axios
        .all([
          this.$api.article.getArticles()
          // this.$api.comment.getComments(),
        ])
        .then(this.$axios.spread(this.initData));
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style scoped>
.info a {
  color: #30a5ff;
  text-decoration: none;
  line-height: 30px;
}
.info a:hover {
  color: #324157;
}
</style>
