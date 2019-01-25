<template>
  <b-row id="header" class="shadow-sm p-0 mb-0 bg-white rounded">
    <b-col sm="0" md="2"></b-col>
    <b-col>
      <b-row>
        <b-col sm="10" md="2">
          <div class="img-wrap">
            <div class="img-align-middle">
              <a href="/"
                ><img
                  class="logo"
                  src="./images/logo.png"
                  v-bind:alt="title"
                  v-bind:title="title"
              /></a>
            </div>
          </div>
        </b-col>
      </b-row>

      <!--搜索遮罩层-->
      <b-modal
        ref="searchModalRef"
        v-model="modalShow"
        hide-footer
        title="全站搜索"
      >
        <!-- 搜索弹窗 -->
        <div id="seacherModal">
          <div>
            <div class="next-seacher">
              <div class="modal-body">
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
              </div>
            </div>
          </div>
        </div>
      </b-modal>
    </b-col>
    <b-col sm="0" md="2"></b-col>
  </b-row>
</template>

<script>
import Vue from "vue";
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
  watch: {
    modalShow: function() {
      if (this.modalShow) {
        let self = this;
        Vue.nextTick().then(function() {
          self.$refs.s.focus();
        });
      }
      // console.log("modalShow:" + this.modalShow);
    }
  },
  data() {
    return {
      s: "",
      modalShow: false
    };
  },
  methods: {
    showModal() {
      this.modalShow = !this.modalShow;
      this.$refs.searchModalRef.show();
    }
  }
};
</script>

<style scoped>
#header {
  padding-bottom: 10px !important;
}
/**
* 图片居中
*/
.img-wrap {
  /*background: #fff;*/
  background: deepskyblue;
}
.img-wrap .img-align-middle:before {
  content: " ";
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}
/*
  ##Device = Mobile
  ##Screen = B/w 1366px
*/
@media (min-width: 320px) and (max-width: 767px) {
  .img-wrap .img-align-middle {
    height: 60px;
  }
  .img-wrap .logo {
    display: inline-block;
    max-height: 30px;
  }
}
/*
  ##Device = iPad
  ##Screen = B/w 1366px
*/
@media (min-width: 768px) and (max-width: 1023px) {
  .img-wrap .img-align-middle {
  }
}
/*
  ##Device = iPad Pro
  ##Screen = B/w 1366px
*/
@media (min-width: 1024px) and (max-width: 1365px) {
  .img-wrap .img-align-middle {
  }
  .img-wrap .logo {
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
</style>
