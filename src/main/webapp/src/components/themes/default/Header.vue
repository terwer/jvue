<template>
  <b-row id="header" class="shadow-sm p-0 mb-0 bg-white rounded">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col sm="12" md="12" lg="12" xl="8">
      <b-row class="header-content">
        <b-col sm="2" md="2" xl="2">
          <div class="img-wrap">
            <a href="/" class="float-left">
              <div class="img-align-middle">
                <img
                  class="logo"
                  src="./images/logo.png"
                  :alt="title"
                  :title="title"
                />
              </div>
            </a>
            <a
              v-if="isMobile"
              @click="showPhoneMenu"
              href="javascript:void(0);"
              class="phone-nav-trigger float-right"
              ><span class="phone-nav-icon"></span
            ></a>
          </div>
        </b-col>
        <b-col
          id="head-nav"
          sm="0"
          md="6"
          lg="6"
          xl="6"
          class="justify-content-center align-self-center"
        >
          <b-nav v-bind:class="(!isMobile || showHeadNav)?'':'hide'">
            <b-nav-item to="/" active>首页</b-nav-item>
            <b-nav-item>随笔</b-nav-item>
            <b-nav-item-dropdown
              id="nav-page"
              text="页面"
              extra-toggle-classes="nav-link-custom"
              right
            >
              <b-dropdown-item>页面默认模板</b-dropdown-item>
              <b-dropdown-item>页面模板01</b-dropdown-item>
              <b-dropdown-item>页面模板02</b-dropdown-item>
              <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item>用户列表页</b-dropdown-item>
              <b-dropdown-item>标签页面</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item-dropdown id="nav-tool" text="工具" extra-toggle-classes="nav-link-custom" right>
              <b-dropdown-item>图集</b-dropdown-item>
              <b-dropdown-item>下载</b-dropdown-item>
              <b-dropdown-item>视频</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item>专题</b-nav-item>
          </b-nav>
        </b-col>
        <!-- 搜索框 -->
        <b-col cols="12" id="searchArea">
          <b-form id="searchform" method="get" action="/">
            <b-input-group prepend="">
              <b-form-input
                id="s"
                v-model="s"
                name="s"
                ref="s"
                type="text"
                placeholder="输入关键词查找..."
              ></b-form-input>
              <b-input-group-append>
                <b-btn id="searchsubmit" type="submit" variant="primary"
                  >搜索</b-btn
                >
              </b-input-group-append>
            </b-input-group>
          </b-form>
        </b-col>
      </b-row>
    </b-col>
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
  </b-row>
</template>
<script>
export default {
  name: "Header",
  props: {
    title: {
      type: [String],
      default() {
        return "";
      }
    }
  },
  // 钩子函数
  mounted() {
    const that = this;
    console.log("init screenWidth:" + that.screenWidth);
    // 屏幕适配
    that.fitScreen();
    window.onresize = () => {
      return (() => {
        if (!that.isInNashorn()) {
          window.screenWidth = document.body.clientWidth;
          that.screenWidth = window.screenWidth;
        }
      })();
    };
  },
  watch: {
    screenWidth() {
      const that = this;
      console.log("screenWidth:" + that.screenWidth);
      // 屏幕适配
      that.fitScreen();
    }
  },
  data() {
    const that = this;
    return {
      s: "",
      isMobile: false,
      screenWidth: that.isInNashorn() ? 0 : document.body.clientWidth, // 屏幕尺寸
      showHeadNav: false
    };
  },
  methods: {
    isInNashorn() {
      const isInNashorn = typeof document === "undefined";
      console.log("isInNashorn:" + isInNashorn);
      return isInNashorn;
    },
    fitScreen() {
      const that = this;
      if (!that.isInNashorn()) {
        // 手机才展开菜单
        that.isMobile = that.screenWidth < 768;
        console.log("isMobile:" + that.isMobile);
      }
    },
    showPhoneMenu() {
      this.showHeadNav = !this.showHeadNav;
    }
  }
};
</script>
<style scoped>
.header-content {
  background: #fff;
}
#header a {
  text-decoration: none;
}
/**
* 图片居中
*/
.img-wrap {
  background: #fff;
}
.img-wrap .img-align-middle:before {
  content: " ";
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}
/* 右侧登录按钮 */
.loginlink a {
  padding: 0.5rem;
}
#searchArea {
  padding-bottom: 10px;
}
/* 手机菜单按钮 */
.phone-nav-icon {
  display: inline-block;
  position: relative;
  width: 25px;
  height: 3px;
  background-color: #333;
}
.phone-nav-icon:before {
  margin-top: -9px;
}
.phone-nav-icon:after {
  margin-top: 9px;
}
.phone-nav-icon:before,
.phone-nav-icon:after {
  content: "";
  display: block;
  width: 25px;
  height: 3px;
  position: absolute;
  background: #333;
  -webkit-transition-property: margin, -webkit-transform;
  transition-property: margin, transform, -webkit-transform;
  -webkit-transition-duration: 300ms;
  transition-duration: 300ms;
}
/* 设备兼容处理 */
/*
##Device = Mobile
##Screen = B/w 320px - 767px
*/
@media (min-width: 320px) and (max-width: 767px) {
  #header .header-right a:hover {
    color: #007bff;
  }
  #head-nav {
    padding: 10px;
  }
  #btn-search {
    padding-left: 0;
  }
  .phone-nav-trigger {
    padding: 15px 5px 10px;
  }
  .img-wrap .img-align-middle {
    height: 60px;
  }
  .img-wrap .logo {
    display: inline-block;
    max-height: 30px;
  }
  .loginlink .search-link {
    padding-left: 0;
  }
}
/*
  ##Device = iPad
  ##Screen = B/w 768px - 1023px
*/
@media (min-width: 768px) and (max-width: 1023px) {
  #header .header-right a:hover {
    color: #007bff;
  }
  #head-nav {
    padding: 0 0 0 25px;
  }
  .img-wrap .img-align-middle {
    width: 130px;
    height: 60px;
  }
  .img-wrap .logo {
    display: inline-block;
    max-height: 30px;
  }
  .loginlink .search-link {
    padding-left: 0;
  }
}
/*
  ##Device = iPad Pro
  ##Screen = B/w 1024px - 1365px
*/
@media (min-width: 1024px) and (max-width: 1365px) {
  #header .header-right a:hover {
    color: #007bff;
  }
  #head-nav {
    padding: 0;
  }
  .img-wrap .img-align-middle {
    height: 60px;
  }
  .img-wrap .logo {
    display: inline-block;
    max-height: 30px;
  }
  .loginlink .search-link {
    padding-left: 0;
  }
}
/*
  ##Device = PC
  ##Screen = B/w 1366px
*/
@media (min-width: 1366px) {
  .img-wrap .img-align-middle {
    height: 75px;
  }
  .img-wrap .logo {
    display: inline-block;
    max-height: 40px;
  }
}
</style>
