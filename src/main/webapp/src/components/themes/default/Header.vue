<template>
  <b-container id="header">
    <b-row>
      <b-col cols="3">
        <div class="imgWrap">
          <div class="align-middle">
            <a href="/"
              ><img class="logo" src="./images/logo.png" alt="Terwer"
            /></a>
          </div>
        </div>
      </b-col>
      <b-col cols="6" class="justify-content-center align-self-center">
        <b-nav>
          <b-nav-item to="/" active>首页</b-nav-item>
          <b-nav-item>随笔</b-nav-item>
          <b-nav-item-dropdown
            id="page"
            text="页面"
            extra-toggle-classes="nav-link-custom"
            variant="success"
            offset="0"
          >
            <b-dropdown-item>页面默认模板</b-dropdown-item>
            <b-dropdown-item>页面模板01</b-dropdown-item>
            <b-dropdown-item>页面模板02</b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item>用户列表页</b-dropdown-item>
            <b-dropdown-item>标签页面</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item>专题</b-nav-item>
          <b-nav-item>图集</b-nav-item>
          <b-nav-item>下载</b-nav-item>
          <b-nav-item>视频</b-nav-item>
        </b-nav>
      </b-col>
      <b-col cols="3" class="justify-content-center align-self-center">
        <div class="header-right">
          <div class="loginlink">
            <a href="javascript:void(0);" @click="showModal"
              ><i class="fa fa-search"></i><span>搜索</span></a
            >
            <a href="/auth/login"><i class="fa fa-user-circle"></i>登录</a>
            <a href="/auth/register"
              ><i class="fa fa-pencil-square-o"></i>注册</a
            >
          </div>
        </div>
      </b-col>
    </b-row>
    <!--搜索遮罩层-->
    <b-modal ref="searchModalRef" hide-footer title="全站搜索">
      <!-- 搜索弹窗 -->
      <div id="seacherModal" tabindex="-1">
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
                    type="text"
                    placeholder="输入关键词查找..."
                  ></b-form-input>
                  <b-input-group-append>
                    <b-btn
                      id="searchsubmit"
                      type="submit"
                      variant="primary"
                      >搜索</b-btn
                    >
                  </b-input-group-append>
                </b-input-group>
              </b-form>
            </div>
          </div>
        </div>
      </div>
      <!--<b-btn variant="danger" block @click="hideModal">关闭</b-btn>-->
    </b-modal>
  </b-container>
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
  data() {
    return {
      s: ""
    };
  },
  methods: {
    showModal() {
      this.$refs.searchModalRef.show();
    }
    // ,
    // hideModal() {
    //   this.$refs.searchModalRef.hide();
    // }
  }
};
</script>

<style scoped>
/**
* 图片居中
*/
#header .imgWrap .align-middle {
  height: 75px;
  text-align: center;
}
#header .imgWrap .align-middle:before {
  content: " ";
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}

#header .imgWrap .logo {
  display: inline-block;
  max-height: 40px;
}
</style>
