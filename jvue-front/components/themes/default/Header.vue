<template>
  <el-row>
    <el-col :xs="24" :sm="24" :md="6">
      <div class="img-wrap">
        <nuxt-link to="/" class="float-left">
          <div class="img-align-middle">
            <img
              class="logo"
              src="../../../assets/logo.png"
              alt="jvue"
              title="jvue"
            />
          </div>
        </nuxt-link>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12">
      <div class="grid-content bg-purple-light">HeaderMenu</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="6">
      <div class="search">
        <el-input v-model="s" placeholder="请输入关键字" clearable>
          <el-button
            slot="append"
            type="primary"
            icon="el-icon-search"
            @click="doSearch"
          >
            搜索
          </el-button>
        </el-input>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getLogger } from "../../../util/logger";
const logger = getLogger("components/themes/default/Header");

export default {
  name: "Header",
  data() {
    return {
      s: "",
      searchLink: "/s/"
    };
  },
  watch: {
    s() {
      this.searchLink = "/s/" + this.s;
    }
  },
  methods: {
    doSearch() {
      logger.info("this.searchLink=>" + this.searchLink);
      // this.$router.push({ path: this.searchLink });
      // this.$router.go(this.searchLink);
    }
  }
};
</script>

<style scoped lang="scss">
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
/* 搜索 */
.search {
  margin: 0 10px;
}
</style>
