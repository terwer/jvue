<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :xs="0" :md="2">&nbsp;</el-col>
        <el-col :xs="24" :md="20">
          <el-main>
            <el-container>
              <el-main>
                <el-container>
                  <el-header>
                    <HeaderTime />
                  </el-header>
                  <el-header>
                    <Header />
                  </el-header>
                  <el-main>
                    <div class="about">
                      <div class="visit">
                        谢谢您，您是第
                        <img :src="message" border="0" alt="访客数" />
                        位访客。
                      </div>
                      <h3>网站简介</h3>
                      <div>
                        <span style="font-size: 24px;margin-left: 40px;">
                          远
                        </span>
                        <span>
                          方的灯塔致力于Java技术栈、后端开发、软件架构、微服务、AI
                          大模型、自然语言处理等相关技术分享。同时也记录个人的一路点滴，欢迎关注。
                        </span>
                      </div>
                      <div>
                        <span style="font-size: 24px;margin-left: 40px;">
                          人
                        </span>
                        <span>
                          生在世，有些事情，有些选择，是不需要理由的。年轻就该去探索，去尝试。人生最大的悲哀不是失败，而是甘于现状，因为他一开始就失败了。
                        </span>
                      </div>
                      <h3>作者简历</h3>
                      <div>
                        <p>
                          2014年7月1日毕业于湖北省长江大学软件工程，目前专注于服务端项目架构、人工智能、自然语言处理等领域的研究。
                        </p>
                        <p>
                          在Web开发领域有深入研究，精通Java服务端开发、AI大模型、自然语言处理等技术。
                        </p>
                      </div>
                      <div>作者邮箱：youweics@163.com</div>
                    </div>

                    <Artalk />
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
import { getLogger } from "../util/logger";
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
import Artalk from "../components/themes/default/Artalk.vue";

const logger = getLogger("pages/index");

export default {
  name: "About",
  components: { HeaderTime, Header, Footer, FriendLink, Artalk },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};
    logger.info("fetch siteConfig finish");

    return { siteConfigObj };
  },
  data() {
    const baseUrl = this.$axios.defaults.baseURL;

    return {
      message: `${baseUrl}/tool/counter?t=${new Date().getTime()}`
    };
  },
  head() {
    return {
      title: "关于我" + " - " + this.siteConfigObj.webname,
      meta: [
        {
          name: "keywords",
          content: "关于,作者,选择,尝试"
        },
        {
          hid: "description",
          name: "description",
          content:
            "人生在世，有些事情，有些选择，是不需要理由的。年轻就该去探索，去尝试。人生最大的悲哀不是失败，而是甘于现状，因为他一开始就失败了。"
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
@import "./webfont.css";
@import "./common.css";
@import "./default.css";
</style>

<style lang="scss" scoped>
.visit {
  font-size: 24px;

  img {
    vertical-align: text-top;
  }
}

.about {
  margin: 40px 20px 20px 20px;
}
</style>
