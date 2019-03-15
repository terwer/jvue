<template>
  <b-row id="header" class="shadow-sm p-0 mb-0 bg-white rounded">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col sm="12" md="12" lg="12" xl="8">
      <b-row class="header-content">
        <b-col sm="2" md="2" xl="2">
          <div class="img-wrap">
            <router-link to="/" class="float-left">
              <div class="img-align-middle">
                <img
                  class="logo"
                  src="../../../assets/logo.png"
                  :alt="title"
                  :title="title"
                />
              </div>
            </router-link>
          </div>
        </b-col>
        <b-col sm="10" class="justify-content-center align-self-center">
          <HeaderMenu />
        </b-col>
        <!-- 搜索框 -->
        <b-col cols="12" id="searchArea">
          <b-form id="searchform" method="get" onsubmit="return false;">
            <b-input-group prepend="">
              <b-form-input
                id="s"
                v-model="s"
                ref="s"
                type="text"
                placeholder="输入关键词查找..."
              ></b-form-input>
              <b-input-group-append>
                <b-btn
                  id="searchsubmit"
                  type="button"
                  variant="primary"
                  @click="doSearch"
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
import { getLogger } from "../../../util/logger";
const logger = getLogger("components/themes/default/Header");
import HeaderMenu from "./HeaderMenu";
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
  watch: {
    s() {
      this.searchLink = "/s/" + this.s;
      this.doSearch();
    }
  },
  data() {
    return {
      s: "",
      searchLink: "/s/"
    };
  },
  methods: {
    doSearch() {
      logger.info("this.searchLink=>" + this.searchLink);
      this.$router.push({ path: this.searchLink });
      // this.$router.go(this.searchLink);
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
