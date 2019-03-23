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
                        <img
                          src="http://www.cutercounter.com/hit.php?id=gvvnqcod&nd=6&style=47"
                          border="0"
                          alt="访客数"
                        />
                        位访客。
                      </div>
                      <h3>网站简介</h3>
                      <div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="font-size: 24px">远</span
                        >方的灯塔是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎关注。
                      </div>
                      <div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="font-size: 24px">人</span
                        >生在世，有些事情，有些选择，是不需要理由的。年轻就该去探索，去尝试。人生最大的悲哀不是失败，而是甘于现状，因为他一开始就失败了。
                      </div>
                      <h3>作者简历</h3>
                      <div>
                        2014年7月1日毕业于湖北省长江大学软件工程，在CRM客户关系管理、电子商务、互联网金融、互联网K12教育等领域有丰富的工作经验。
                      </div>
                      <div>
                        专注于项目架构、性能优化、算法研究。在Web开发领域有深入研究，精通Java，目前专注于服务端开发。业余时间喜欢探索移动互联网。
                      </div>
                      <div>作者邮箱：youweics@sina.com</div>
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
import { getLogger } from "../util/logger";
import HeaderTime from "../components/themes/default/HeaderTime";
import Header from "../components/themes/default/Header";
import Footer from "../components/themes/default/Footer";
import FriendLink from "../components/themes/default/FriendLink";
const logger = getLogger("pages/index");

export default {
  name: "About",
  components: { HeaderTime, Header, Footer, FriendLink },
  async asyncData({ $axios }) {
    const siteConfigResult = await $axios.$post("/site/config/list");
    const siteConfigObj =
      siteConfigResult.status === 1 ? siteConfigResult.data : {};
    logger.info("fetch siteConfig finish");

    return { siteConfigObj };
  },
  head() {
    return {
      title: "关于我" + " - " + this.siteConfigObj.webname,
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

<style lang="scss" scoped>
@import "./common.css";
@import "./default.css";
</style>

<style lang="scss" scoped>
.visit {
  font-size: 24px;
}
.about {
  margin: 40px 20px 20px 20px;
}
</style>
