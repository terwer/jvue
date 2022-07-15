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
                    <el-row>
                      <el-col :xs="24" :xl="18">
                        <div id="essay" class="essay">
                          <!-- 正文开始 -->
                          <div class="lay-blog">
                            <div class="container-wrap">
                              <div class="container container-message">
                                <div id="contar-wrap" class="contar-wrap">
                                  <form class="layui-form" action="">
                                    <div
                                      class="layui-form-item layui-form-text"
                                    >
                                      <textarea
                                        id="LAY-msg-content"
                                        v-model="newEssay"
                                        class="layui-textarea"
                                        style="resize:none"
                                      ></textarea>
                                    </div>
                                  </form>

                                  <div class="item-btn">
                                    <button
                                      id="item-btn"
                                      class="layui-btn layui-btn-normal"
                                      @click="publishEssay"
                                    >
                                      提交
                                    </button>
                                  </div>

                                  <div id="LAY-msg-box">
                                    <div class="info-box">
                                      <div
                                        v-for="timeline in postListArray"
                                        :key="timeline.postId"
                                        class="info-item"
                                      >
                                        <img
                                          class="info-img"
                                          src="https://tvax1.sinaimg.cn/crop.0.0.540.540.180/0075uTFdly8fs75paasl1j30f00f0afd.jpg"
                                          alt=""
                                        />
                                        <div class="info-text">
                                          <h4>{{ timeline.postDate }}</h4>
                                          <div class="title count">
                                            <span class="name">倚楼听雨</span>
                                          </div>
                                          <div
                                            class="info-intr"
                                            v-html="timeline.postContent"
                                          ></div>
                                          <div class="read-more">
                                            <span
                                              v-if="
                                                timeline.postContent &&
                                                  timeline.postContent.length >=
                                                    50
                                              "
                                            >
                                              <a target="_blank">阅读全文</a>
                                            </span>
                                          </div>
                                        </div>
                                      </div>
                                      <div class="load-more text-center">
                                        <p></p>
                                        <p></p>
                                        <p
                                          class="loadmore-btn"
                                          @click="loadmore"
                                        >
                                          {{ loadText }}
                                        </p>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </el-col>
                    </el-row>
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
import FriendLink from "../../components/themes/default/FriendLink";
import HeaderTime from "@/components/themes/default/HeaderTime";
import Header from "@/components/themes/default/Header";
import Footer from "@/components/themes/default/Footer";

export default {
  name: "Essay",
  components: { HeaderTime, Header, Footer, FriendLink },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const siteConfigObj = siteConfigResult.data;

    const postsResult = await $axios.$post("/blog/post/list", {
      postType: "essay",
      postStatus: "publish",
      pageNum: 1,
      pageSize: 10
    });
    const postListArray = postsResult.status === 1 ? postsResult.data.list : [];

    return { siteConfigObj, postListArray };
  },
  data() {
    return {
      total: 0,
      pageSize: 10,
      currentPage: 1,
      showNores: false,
      loadText: "加载更多",
      postListArray: [],
      newEssay: ""
    };
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
  },
  methods: {
    async loadmore() {
      this.showNores = false;
      this.loadText = "加载中...";
      const postsResult = await this.$axios.$post("/blog/post/list", {
        postType: this.type,
        postStatus: "publish",
        search: this.keyword,
        pageNum: ++this.currentPage,
        pageSize: this.pageSize
      });
      // console.log(postsResult);
      if (postsResult.status === 1) {
        this.total = postsResult.data.total;
        if (postsResult.data.list.length > 0) {
          // 渲染数据
          for (const idx in postsResult.data.list) {
            const post = postsResult.data.list[idx];
            this.postListArray.push(post);
          }
          this.loadText = "加载更多";
        } else {
          this.showNores = true;
          this.loadText = "加载完成";
        }
      }
    },
    async publishEssay() {
      console.log("start publish essay:" + this.newEssay);
      const result = await this.loadmore();
      console.log("result=>", result);
    }
  }
};
</script>

<style lang="scss">
@import "../common.css";
@import "../default.css";
@import "../essay.css";

.essay {
  color: #ffffff;
}
.read-more a {
  color: #1e9fff;
}
.load-more {
  color: #ffb800;
}
.loadmore-btn {
  cursor: pointer;
}
.like em {
  font-style: normal;
  font-size: 12px;
  color: red;
}
</style>
