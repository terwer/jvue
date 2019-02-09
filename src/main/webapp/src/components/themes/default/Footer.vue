<template>
  <b-row id="footer">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col sm="12" md="12" lg="12" xl="2" class="footer-item">
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
            <p class="wpm_prepend">
              订阅本站获取每周更新邮件提醒，不错过任何一篇文章！
            </p>
            <p class="wpm_email">
              <label class="wpm_emaillabel" for="wpm_email">邮箱：</label>
              <input
                class="wpm_emailinput"
                id="wpm_email"
                name="wpm_email"
                placeholder="请输入您的邮箱地址"
                type="text"
                value=""
              />
            </p>
            <p class="wpm_submit">
              <input
                name="submit"
                class="btn wpm_submitbtn"
                type="submit"
                value="订阅"
              />
            </p>
          </form>
        </div>
      </div>
    </b-col>
    <b-col sm="12" md="12" lg="12" xl="2" class="footer-item">
      <div class="guanzhu">
        <div class="footertit">
          <h2>关注&amp;交流</h2>
        </div>
        <div class="ewm">
          <div>
            <div>
              <p>
                技术之路，路漫漫其修远兮，吾将上下而求索。
              </p>
              <p>
                欢迎关注公众号"架构设计漫谈"，一起分享技术，探讨人生。
              </p>
              <div class="ewmimg">
                <img src="../../../assets/mp-qrcode.png" alt="Terwer" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </b-col>
    <b-col sm="12" md="12" lg="12" xl="4" class="footer-item">
      <div class="contact">
        <div class="contactcon">
          <b-row>
            <b-col cols="12">
              <div class="footertit">
                <h2>联系作者</h2>
              </div>
              <p>
                如果您有相关合作事宜，可以通过以下方式联系我，我会在收到消息后第一时间回复处理！<br />
              </p>
            </b-col>
            <b-col xl="6">邮箱：youweics@sina.com</b-col>
            <b-col xl="6"
              >微博：<a href="https://weibo.com/youweics" target="_blank"
                >youweics</a
              ></b-col
            >
            <b-col xl="6">QQ：1035136784（倚楼听雨）</b-col>
            <b-col xl="6">微信：15986685029（hiterwer）</b-col>
            <b-col cols="12" class="copyright">
              <br />
              Copyright © 2011-2018 <a href="/" target="_blank">webname</a>
              <span class="next-line"><br /></span>
              All Rights Reserved
            </b-col>
            <b-col>
              博客已稳定运行： 2年303天22时22分48秒 &nbsp;
              <!-- cnzz统计 -->
              <span id="cnzz_stat_icon_4445524"></span>
            </b-col>
          </b-row>
        </div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "Footer"
};
</script>

<style scoped>
#footer {
  padding: 0;
  background: #2e3033;
  color: rgba(255, 255, 255, 0.42);
  margin-top: 10px;
}
#footer p {
  margin-bottom: 5px;
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
.copyright {
  margin-top: 15px;
}
.guanzhu {
  padding: 0;
}

.guanzhu .row {
  margin-left: 0;
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
/* 设备兼容处理 */
/*
##Device = iPad
##Screen = B/w 768px - 1023px
*/
@media (min-width: 768px) and (max-width: 1023px) {
  .next-line {
    display: none;
  }
}
/*
##Device = iPad Pro
##Screen = B/w 1024px - 1365px
*/
@media (min-width: 1024px) and (max-width: 1365px) {
  .next-line {
    display: none;
  }
}
/*
##Device = PC
##Screen = B/w 1366px
*/
@media (min-width: 1366px) {
  .next-line {
    display: none;
  }
}
</style>
