<template>
  <el-row>
    <el-col :xs="24" :md="16">
      <PostList :post-list="postListArray" />
      <p v-if="showNores" class="nores">~ 我是有底线滴 ~</p>
      <div id="pagination">
        <div class="loadmore-dark">
          <a id="btn-loadmore" href="javascript:void(0);" @click="loadmore">
            {{ loadText }}
          </a>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :md="8">
      <Aside />
    </el-col>
  </el-row>
</template>

<script>
import PostList from "./PostList";
import Aside from "./Aside";
export default {
  name: "Body",
  components: { Aside, PostList },
  props: {
    postList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      total: 0,
      pageSize: 10,
      currentPage: 1,
      showNores: false,
      loadText: "加载更多",
      postListArray: this.postList
    };
  },
  methods: {
    async loadmore() {
      this.showNores = false;
      this.loadText = "加载中...";
      const postsResult = await this.$axios.$post("/blog/post/list", {
        postStatus: "publish",
        pageNum: ++this.currentPage,
        pageSize: this.pageSize
      });
      // console.log(postsResult);
      if (postsResult.status === 1) {
        this.total = postsResult.data.total;
        if (postsResult.data.list.length > 0) {
          // 渲染数据
          for (const idx in postsResult.data.list) {
            const post = postsResult.data.list[idx];
            this.postListArray.push(post);
          }
          this.loadText = "加载更多";
        } else {
          this.showNores = true;
          this.loadText = "加载完成";
        }
      }
    }
  }
};
</script>

<style scoped lang="scss">
.nores {
  color: #f3f3f3;
  text-align: center;
}
#pagination {
  padding: 10px;
}
</style>
