<template>
  <b-row id="header" class="shadow-sm p-0 mb-0 bg-white rounded">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col sm="12" md="12" lg="12" xl="8">
      <b-row>
        <b-col sm="2" md="2" xl="2">
          <div class="img-wrap">
            <a href="/" class="float-left">
              <div class="img-align-middle">
                <img
                  class="logo"
                  src="./images/logo.png"
                  v-bind:alt="title"
                  v-bind:title="title"
                />
              </div>
            </a>
            <a
              v-if="isMobile"
              href="javascript:void(0);"
              @click="showPhoneMenuCollapse = !showPhoneMenuCollapse"
              class="phone-nav-trigger float-right"
              ><span class="phone-nav-icon"></span
            ></a>
          </div>
        </b-col>
        <b-col
          id="head-menu"
          sm="0"
          md="6"
          lg="6"
          xl="6"
          class="justify-content-center align-self-center"
        >
          <b-collapse v-model="showPhoneMenuCollapse" id="phoneMenuCollapse">
            <b-nav>
              <b-nav-item to="/" active>首页</b-nav-item>
              <b-nav-item>随笔</b-nav-item>
              <ul class="nav">
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="page"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    页面
                  </a>
                  <div class="dropdown-menu" aria-labelledby="page">
                    <a class="dropdown-item" href="#">页面默认模板</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">页面模板01</a>
                    <a class="dropdown-item" href="#">页面模板02</a>
                    <a class="dropdown-item" href="#">用户列表页</a>
                    <a class="dropdown-item" href="#">标签页面</a>
                  </div>
                </li>
              </ul>
              <ul class="nav">
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="tool"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    工具
                  </a>
                  <div class="dropdown-menu" aria-labelledby="tool">
                    <a class="dropdown-item" href="#">图集</a>
                    <a class="dropdown-item" href="#">下载</a>
                    <a class="dropdown-item" href="#">视频</a>
                  </div>
                </li>
              </ul>
              <b-nav-item>专题</b-nav-item>
            </b-nav>
          </b-collapse>
        </b-col>
        <b-col
          md="4"
          lg="4"
          xl="2"
          class="justify-content-center align-self-center"
        >
          <div
            :class="
              showPhoneMenuCollapse ? 'header-right' : 'header-right hide'
            "
          >
            <div class="loginlink">
              <a
                id="btn-search"
                href="javascript:void(0);"
                @click="showSearchCollapse = !showSearchCollapse"
              >
                <i class="fa fa-search"></i><span>搜索</span></a
              >
              <a href="/auth/login"><i class="fa fa-user-circle"></i>登录</a>
              <a href="/auth/register"
                ><i class="fa fa-pencil-square-o"></i>注册</a
              >
            </div>
          </div>
        </b-col>
        <!-- 搜索框 -->
        <b-col cols="12">
          <div>
            <b-collapse v-model="showSearchCollapse" id="searchCollapse">
              <b-form id="searchform" method="get" action="/">
                <!-- Using components -->
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
            </b-collapse>
          </div>
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
  watch: {},
  // 钩子函数
  // mounted() {
  //   const that = this;
  //   // window.onresize = () => {
  //   //   return (() => {
  //   //     window.screenWidth = document.body.clientWidth;
  //   //     that.screenWidth = window.screenWidth;
  //   //   })();
  //   // };
  //
  //   console.log("screenWidth:" + that.screenWidth);
  //   // 手机才展开菜单
  //   if (that.screenWidth < 768) {
  //     that.isMobile = true;
  //     that.showPhoneMenuCollapse = false;
  //   }
  // },
  data() {
    return {
      s: "",
      isMobile: false,
      // screenWidth: 1400,// document.body.clientWidth, // 屏幕尺寸
      showSearchCollapse: false,
      showPhoneMenuCollapse: true
    };
  },
  methods: {}
};
</script>

<style scoped>
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
.nav li .dropdown-menu {
  min-width: 0 !important;
}
.nav li:hover > .dropdown-menu {
  position: absolute;
  display: block;
}
.dropdown-menu a:hover {
  background: #007bff;
  color: #fff;
}
.loginlink a {
  padding: 0.5rem;
}
#searchCollapse {
  padding: 10px 0;
}
#phoneMenuCollapse {
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
  #head-menu {
    padding: 0;
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
  #head-menu {
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
  #head-menu {
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
