<template>
  <el-row id="footer">
    <el-main class="footer-main">
      <el-row>
        <el-col>
          <div class="guanzhu">
            <div class="footertit">
              <h2>关注&amp;交流</h2>
            </div>
            <div class="ewm">
              <div>
                <div>
                  <div class="page">
                    技术之路，路漫漫其修远兮，吾将上下而求索。
                  </div>
                  <div class="page">
                    欢迎关注公众号"架构设计漫谈"，一起分享技术，探讨人生。
                  </div>
                  <div class="page ewmimg">
                    <img src="../../../assets/mp-qrcode.png" alt="Terwer" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="footertit">
            <h2>联系作者</h2>
          </div>
          <div class="page" style="padding-right: 20px">
            如果您有相关合作事宜，可以通过以下方式联系我，我会在收到消息后第一时间回复处理！
          </div>
          <div class="page">
            <br />
            Github：https://github.com/terwer
          </div>
          <div class="page">
            邮箱：youweics@163.com
          </div>
          <div class="page">
            微信：15986685029（hiterwer）
          </div>
          <div class="page">
            QQ：1035136784（倚楼听雨）
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="footertit">
            <h2>更多信息</h2>
          </div>
          <div class="page">
            <a
              href="https://v.yunaq.com/certificate?domain=www.anquan.org&amp;from=label&amp;code=90040"
              target="_blank"
            >
              <img
                alt="anquan"
                src="https://aqyzmedia.yunaq.com/labels/label_sm_90040.png"
              />
            </a>
          </div>
          <!-- 运行信息 -->
          <div class="page">博客已稳定运行： 2年303天22时22分48秒<br /></div>
          <div class="page">
            运行环境：docker、jdk_1.8_201、maven_3.6.0<br />
            Spring Boot v2.1.3.RELEASE, Spring v5.1.5.RELEASE<br />
            node14.20.0、vue_2.6.9、nuxt_2.4.0
          </div>
          <!-- 备案信息 -->
          <div class="page copyright">
            <a
              href="https://beian.miit.gov.cn"
              rel="external nofollow"
              target="_blank"
            >
              {{ siteConfig.beianinfo }}
            </a>
            <!-- 百度统计 -->
            <script>
              var _hmt = _hmt || [];
              (function() {
                var hm = document.createElement("script");
                hm.src =
                  "https://hm.baidu.com/hm.js?8cf152054e4b32b33cbc68fef515bbb5";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
              })();
            </script>
          </div>
          <div class="page copyright">
            Copyright © 2011-{{ nowYear }}
            <a :href="siteConfig.weburl" target="_blank">
              {{ siteConfig.webname }}
            </a>
            <span class="next-line"></span>
            All Rights Reserved
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-row>
</template>

<script>
export default {
  name: "Footer",
  props: {
    siteConfig: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      nowYear: new Date().getFullYear()
    };
  }
};
</script>

<style scoped lang="scss">
a {
  color: #00a4ff;
  text-decoration: none;
}
#footer {
  padding: 0;
  background: #ffffff;
  color: #333333;
  // background: #2e3033;
  //color: rgba(255, 255, 255, 0.42);
  margin-top: 10px;
  .footer-main {
    padding: 0 !important;
    text-align: center;
  }
}
.dingyue {
  //padding: 0 20px;
}
.guanzhu {
  //padding: 0 20px;
}
.contact {
  //padding: 0 20px;
}
#footer .page {
  line-height: 30px;
  font-size: 14px;
}
#footer .copyright {
  text-align: center;
}
.footertop {
  padding-bottom: 30px;
}
.footertit h2 {
  font-size: 18px;
  color: #333333;
  margin-bottom: 20px;
  margin-top: 20px;
}
.guanzhu .row {
  margin-left: 0;
}
.ewmimg {
  margin-top: 10px;
}
.ewmimg img {
  width: 25%;
  height: auto;
  display: block;
  margin: auto;
}
</style>
