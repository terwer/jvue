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
                            postObj.name === ''
                              ? '/post-dark/' + postObj.id + '.html'
                              : '/post-dark/' + postObj.name + '.html'
                          "
                        >
                          <h1>
                            {{ postObj.title }}
                          </h1>
                        </nuxt-link>
                      </div>

                      <!-- 文章详情 -->
                      <div
                        id="postContent"
                        v-highlight
                        v-html="postObj.content"
                      ></div>

                      <h1 v-if="errorMessage !== ''" class="error-message-dark">
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
import { getLogger } from "../../util/logger";
import HeaderTime from "../../components/themes/dark/HeaderTime";
import Header from "../../components/themes/dark/Header";
import Footer from "../../components/themes/dark/Footer";
import FriendLink from "../../components/themes/dark/FriendLink";
import { inBrowser } from "../../util/dom";

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
  asyncData: async function(context) {
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
    let postObj = {};
    let errorMessage = "";
    if (postResult.status === 1) {
      postObj = postResult.data;
    } else {
      errorMessage = postResult.msg;
    }
    logger.info("fetch siteConfig and post finish");

    return { siteConfigObj, postObj, errorMessage };
  },
  head() {
    return {
      title:
        this.errorMessage === ""
          ? this.postObj.title + " - " + this.siteConfigObj.webname
          : this.errorMessage,
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
  },
  created() {
    if (inBrowser && window.MathJax) {
      // 高亮数学公式
      MathJax.Hub.Config({
        tex2jax: {
          inlineMath: [["$", "$"], ["\(", "\)"]]
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
@import "../dark.css";
@import "../../plugins/lib/vue-hljs/vs2015.css";
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
    font-size: 14px;
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
</style>

<style lang="scss" scoped></style>
