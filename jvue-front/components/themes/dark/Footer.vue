<template>
  <el-row id="footer">
    <el-col :xs="24" :md="5">
      <div class="dingyue">
        <div class="footertit">
          <h2>订阅本站</h2>
        </div>
        <div class="dyform">
          <form class="wpm_form" method="post">
            <input
              class="wpm_hiddenfield"
              name="wpm_form"
              type="hidden"
              value="1"
            />
            <div class="page wpm_prepend">
              订阅本站获取每周更新邮件提醒，不错过任何一篇文章！更有精彩福利放送哦~
            </div>
            <div class="wpm_email">
              <label class="wpm_emaillabel" for="wpm_email">邮箱：</label>
              <input
                id="wpm_email"
                class="wpm_emailinput"
                name="wpm_email"
                placeholder="请输入您的邮箱地址"
                type="text"
                value=""
              />
            </div>
            <div class="wpm_submit">
              <button ref="submit" class="btn wpm_submitbtn">订阅</button>
            </div>
          </form>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :md="5">
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
    <el-col :xs="24" :md="14">
      <el-row class="contact">
        <el-col :xs="24" :md="12">
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
            邮箱：youweics@sina.com
          </div>
          <div class="page">
            微信：15986685029（hiterwer）
          </div>
          <div class="page">
            QQ：1035136784（倚楼听雨）
          </div>
        </el-col>
        <el-col :xs="24" :md="12">
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
          <!-- 备案信息 -->
          <div class="page">
            <a
              href="http://www.miitbeian.gov.cn/"
              rel="external nofollow"
              target="_blank"
            >
            </a>
            <!-- cnzz统计 -->
            <span id="cnzz_stat_icon_4445524">
              <a
                href="https://www.cnzz.com/stat/website.php?web_id=4445524"
                target="_blank"
                title="站长统计"
              >
                <img
                  border="0"
                  hspace="0"
                  vspace="0"
                  src="http://icon.cnzz.com/img/pic.gif"
                  alt="cnzz"
                />
              </a>
            </span>

            <span>
              <a href="https://beian.miit.gov.cn/" target="_blank">
                {{ siteConfig.beianinfo }}
              </a>
            </span>
          </div>
          <div class="page">
            Copyright © 2011-{{ nowYear }}
            <a :href="siteConfig.weburl" target="_blank">
              {{ siteConfig.webname }}
            </a>
            <span class="next-line"></span>
            All Rights Reserved
          </div>
          <!-- 运行信息 -->
          <div class="page">博客已稳定运行： 10年303天22时22分48秒<br /></div>
          <div class="page">
            运行环境：docker、jdk_1.8_201、maven_3.6.0<br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vue_2.6.9、nuxt_2.4.0
          </div>
        </el-col>
      </el-row>
    </el-col>
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
#footer {
  padding: 0;
  background: #2e3033;
  color: rgba(255, 255, 255, 0.42);
  margin-top: 10px;
}
a {
  color: #c3e88d !important;
  text-decoration: none;
}
a:hover {
  color: #ffcb6b !important;
}
.dingyue {
  padding: 0 20px;
}
.guanzhu {
  padding: 0 20px;
}
.contact {
  padding: 0 20px;
}
#footer .page {
  line-height: 30px;
  font-size: 14px;
}
.footertop {
  padding-bottom: 30px;
}
.footertit h2 {
  font-size: 18px;
  color: #fff;
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
  width: 100%;
  height: auto;
  display: block;
  margin-bottom: 10px;
}
.wpm_emaillabel {
  display: none;
}
.wpm_emailinput {
  border: 1px solid #f1f1f1;
  background: #f6f6f6;
  color: #a3a9b0;
  padding: 10px;
  margin-bottom: 20px;
  display: block;
  width: 100%;
  min-height: 30px;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  border-radius: 2px;
  margin-top: 20px;
}
.wpm_submit .wpm_submitbtn {
  width: 100%;
  padding: 10px;
  min-height: 30px;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  border-radius: 2px;
  background: #00a4ff;
  color: #fff;
  border-color: #00a4ff;
}
.wpm_submit .wpm_submitbtn:hover {
  color: #fff;
}
</style>
