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
                  src="../../../assets/logo.png"
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
          v-if="!isMobile || showHeadNav"
          sm="10"
          class="justify-content-center align-self-center"
        >
          <HeaderMenu />
        </b-col>
        <!-- 搜索框 -->
        <b-col cols="12" id="searchArea">
          <b-form id="searchform" method="get" :action="searchLink">
            <b-input-group prepend="">
              <b-form-input
                id="s"
                v-model="s"
                name="kw"
                ref="s"
                type="text"
                placeholder="输入关键词查找..."
              ></b-form-input>
              <b-input-group-append>
                <a :href="searchLink">
                  <b-btn id="searchsubmit" type="button" variant="primary"
                    >搜索</b-btn
                  >
                </a>
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
var inBrowser = require("../../../util/dom").inBrowser;
import HeaderMenu from "./HeaderMenu.vue";

export default {
  name: "Header",
  components: {
    HeaderMenu
  },
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
        if (inBrowser) {
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
    },
    s() {
      const that = this;
      that.searchLink = "/s/" + that.s;
      console.log(that.searchLink);
    }
  },
  data() {
    return {
      s: "",
      searchLink: "/s/",
      isMobile: false,
      screenWidth: inBrowser ? document.body.clientWidth : 0, // 屏幕尺寸
      showHeadNav: false
    };
  },
  methods: {
    fitScreen() {
      const that = this;
      if (inBrowser) {
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
