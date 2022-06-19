<template>
  <el-container>
    <el-main class="el-container-dark">
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main class="el-main-dark">
            <el-container>
              <el-main class="el-main-dark">
                <el-container>
                  <el-header class="el-header-dark">
                    <HeaderTime />
                  </el-header>
                  <el-header class="el-header-dark">
                    <Header />
                  </el-header>
                  <el-main class="el-main-dark">
                    <el-row>
                      <el-col :xs="24" :xl="18">
                        <div id="post" class="post-dark">
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
                            <nuxt-link
                              :to="
                                postObj.postSlug === ''
                                  ? '/post/' + postObj.postId + '.html'
                                  : '/post/' + postObj.postSlug + '.html'
                              "
                            >
                              <h1>
                                {{ postObj.postTitle }}
                              </h1>
                            </nuxt-link>
                          </div>

                          <!-- 文章详情 -->
                          <div
                            id="postContent"
                            v-highlight
                            v-html="postObj.postContent"
                          ></div>

                          <h1
                            v-if="errorMessage !== ''"
                            class="error-message-dark"
                          >
                            {{ errorMessage }}
                          </h1>

                          <div class="copy">
                            <p>作者：Terwer</p>
                            <p>首发：远方的灯塔</p>
                            <p>
                              原创内容，转载请注明出处！
                            </p>
                          </div>
                        </div>
                      </el-col>
                      <el-col :xs="0" :xl="6">
                        <div id="postMenu">
                          <div>
                            <p>文章目录</p>
                          </div>
                          <div id="postMenuWapper">
                            <p>目录加载中...</p>
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
/* eslint no-undef: 0 */ // --> OFF
/* eslint nuxt/no-globals-in-created: 0 */ // --> OFF
/* eslint vue/no-v-html: 0 */ // --> OFF
/* eslint no-useless-escape: 0 */ // --> OFF
/* eslint no-new: 0 */ // --> OFF
import Catalog from "progress-catalog";
import { getLogger } from "../../util/logger";
import HeaderTime from "../../components/themes/dark/HeaderTime";
import Header from "../../components/themes/dark/Header";
import Footer from "../../components/themes/dark/Footer";
import FriendLink from "../../components/themes/dark/FriendLink";
import { inBrowser } from "../../util/dom";
// 引入

const logger = getLogger("pages/post");

export default {
  components: { HeaderTime, Header, Footer, FriendLink },
  asyncData: async function(context) {
    const siteConfigResult = await context.$axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};

    const id = context.route.params.id.replace(/\.[^/.]+$/, "");
    const postParams = {};
    const postResult = await context.$axios.$post(
      "/blog/post/detail/" + id,
      postParams
    );
    let postObj = {};
    let errorMessage = "";
    if (postResult.status === 1) {
      postObj = postResult.data;
    } else {
      errorMessage = postResult.msg;
    }

    return { siteConfigObj, postObj, errorMessage };
  },
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
  head() {
    return {
      title: this.postObj.postTitle + "-" + this.siteConfigObj.webname,
      meta: [
        {
          name: "keywords",
          content: this.siteConfigObj.keywords
        },
        {
          hid: "description",
          content: this.siteConfigObj.postDesc
        }
      ],
      script: [
        {
          src:
            "https://cdn.staticfile.org/mathjax/2.7.5/MathJax.js?config=TeX-AMS-MML_HTMLorMML"
        }
      ]
    };
  },
  async mounted() {
    // 更新文章浏览数
    await this.$axios.$post("/blog/post/updateHits", {
      postId: this.postObj.id,
      hits: ++this.postObj.hits
    });

    // 文章目录
    new Catalog({
      contentEl: "postContent",
      catalogEl: "postMenuWapper",
      selector: ["h1", "h2", "h3", "h4", "h5", "h6"],
      cool: true
    });
  },
  created() {
    if (inBrowser && window.MathJax) {
      // 高亮数学公式
      MathJax.Hub.Config({
        tex2jax: {
          inlineMath: [
            ["$", "$"],
            ["\(", "\)"]
          ]
        }
      });

      this.$nextTick(function() {
        MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
      });
      logger.info("MathJax hilight success");
    }
  }
};
</script>

<style lang="scss">
@import "../common.css";
@import "../../plugins/lib/vue-hljs/vs2015.css";
@import "../dark.css";
@import "../../node_modules/progress-catalog/src/progress-catalog.css";

#postMenu {
  margin-top: 20px;
  position: fixed;
  p {
    color: #f3f3f3;
  }
  .cl-wrapper li > .cl-link {
    max-width: 350px;
    padding: 0 10px;
  }
  .cl-wrapper li > .cl-link:hover {
    color: #ffcb6b;
    background-color: #181818;
  }
}

.post-dark {
  margin: 20px;
  a {
    color: #409eff;
    line-height: 1.5;
    text-decoration: none;
  }
  a:hover {
    color: #ffcb6b;
  }
  p {
    color: #f3f3f3;
  }
}
.post-dark #postTitle {
  h1 {
    color: #409eff;
    line-height: 1.5;
    text-decoration: none;
    border-bottom: 1px solid #ddd;
    font-size: 28px;
    font-weight: bold;
    margin: 20px 0 10px;
    padding-bottom: 5px;
  }
  h1:hover {
    color: #c792ea;
  }
}

.post-dark #postContent {
  h1 {
    color: #c792ea;
    font-size: 28px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h2 {
    color: #ffcb6b;
    font-size: 21px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h3 {
    color: #b8d7a3;
    font-size: 18px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h4 {
    color: #409eff;
    font-size: 16px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h5 {
    color: #629755;
    font-size: 14px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  h6 {
    color: #ff5370;
    font-size: 12px;
    font-weight: bold;
    line-height: 1.5;
    margin: 10px 0;
  }
  p {
    // 图片自适应
    img {
      max-width: 100% !important;
    }
  }
}

.post-dark {
  .copy p {
    color: #ffcb6b;
  }
}

.error-message-dark {
  font-size: 48px;
  text-align: center;
  color: #ff5370;
  line-height: 1.6;
}

/* 顺序列表 */
.post-dark {
  ul,
  ol {
    li {
      color: #f3f3f3;
      code {
        font-size: 16px;
      }
      p {
        code {
          font-size: 16px;
        }
      }
    }
  }

  blockquote {
    color: #67c23a;
    background-color: #181818;
    border: solid 1px;
    border-radius: 4px;
    padding: 0 10px;
    margin: 10px 0;
    code {
      font-size: 16px;
    }
    p {
      code {
        font-size: 16px;
      }
    }
  }
}
</style>

<style lang="scss" scoped></style>
