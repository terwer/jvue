<template>
  <b-row id="footer">
    <b-col cols="2"></b-col>
    <b-col>
      <div class="contact">
        <div class="footertit">
          <h2>联系作者</h2>
        </div>
        <div class="contactcon">
          <p style="line-height: 24px;">
            如果您有想过合作事宜，可以通过以下方式联系我们<br />
            邮箱：youweics@sina.com<br />
            QQ：1035136784（倚楼听雨）<br />
            微信：15986685029（hiterwer）
          </p>
          <p class="copyright">
            Copyright © 2011-2018 <a href="/" target="_blank">webname</a> ·
            保留所有权益
          </p>
          <p id="cnzz">
            <a target="_blank" href="http://www.miitbeian.gov.cn/">
              <span>beianinfo</span>
            </a>
            <!--  cnzz数据统计 -->
            <!-- <script type="text/javascript">
                                  var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
                                  document.write(unescape("%3Cspan id='cnzz_stat_icon_4445524'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s5.cnzz.com/stat.php%3Fid%3D4445524%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
                              </script> -->
            <span id="cnzz_stat_icon_4445524">
              <a
                href="http://www.cnzz.com/stat/website.php?web_id=4445524"
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
            <!-- 改为异步代码，防止警告
                              <script type="text/javascript">
                                  var cnzz_s_tag = document.createElement('script');
                                  cnzz_s_tag.type = 'text/javascript';
                                  cnzz_s_tag.async = true;
                                  cnzz_s_tag.charset = 'utf-8';
                                  cnzz_s_tag.src = 'https://w.cnzz.com/c.php?id=4445524&async=1';
                                  var root_s = document.getElementsByTagName('script')[0];
                                  root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
                              </script>
                               -->
          </p>
        </div>
      </div>
    </b-col>
    <b-col>
      <div class="guanzhu">
        <div class="footertit">
          <h2>关注&amp;交流</h2>
        </div>
        <div class="ewm">
          <div class="row">
            <div class="col-xs-12">
              <p class="text-venter">
                技术之路，路漫漫其修远兮，吾将上下而求索。欢迎关注公众号"架构设计漫谈"，一起分享技术，探讨人生。
              </p>
              <p></p>
              <div class="ewmimg">
                <img src="./images/mp-qrcode.png" alt="Terwer" />
                <p class="text-center">微信公众号</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </b-col>
    <b-col>
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
    <b-col cols="2"></b-col>
  </b-row>
</template>

<script>
export default {
  name: "Footer"
};
</script>

<style scoped>
#footer {
  padding-top: 30px;
  background: #2e3033;
  color: rgba(255, 255, 255, 0.42);
  margin-top: 30px;
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
#footer p {
  margin-bottom: 5px;
  font-size: 13px;
}
.copyright {
  margin-top: 15px;
}
.guanzhu {
  padding: 0 52px;
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
</style>
