<template>
  <div>
    <el-input
      class="top-search"
      v-model="s"
      placeholder="请输入关键字"
      clearable
      @keyup.enter.native="doSearch"
    >
      <el-button
        slot="append"
        class="s-dark-btn"
        type="primary"
        icon="el-icon-search"
        @click="doSearch"
      >
        搜索
      </el-button>
    </el-input>
    <el-table :data="articleDatas" border stripe style="width: 100%">
      <el-table-column prop="id" label="id" width="60"></el-table-column>
      <el-table-column
        width="618"
        prop="title"
        label="标题"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="category"
        label="分类"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="tags"
        label="标签"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="typeText"
        label="类型"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="statusText"
        label="状态"
        width="100"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="publish"
        label="发布日期"
        width="160"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="modified"
        label="修改日期"
        width="160"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column fixed="right" label="操作" width="315">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="handlePreview(scope.row.id)"
          >
            预览
          </el-button>
          <el-button size="small" @click="handleEdit(scope.row.id)">
            编辑
          </el-button>
          <el-button
            size="small"
            type="danger"
            :disabled="scope.row.showDelete"
            @click="handleDelete(scope.row.id)"
          >
            删除
          </el-button>
          <el-button
            size="small"
            type="warning"
            :disabled="scope.row.showTrash"
            @click="handleTrash(scope.row.id)"
          >
            移到回收站
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="admin-page">
      <el-pagination
        layout="total,prev, pager, next"
        @current-change="init"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import serverConfig from "../../../server-config";

  export default {
  data: function () {
    return {
      s: "",
      articleDatas: [],
      total: 0,
      pageSize: 10,
      currentPage: 1
    };
  },
  methods: {
    doSearch() {
      this.init(this.$route.query.page);
    },
    handlePreview(id){
      window.open(serverConfig.frontUrl + "post-dark/" + id + ".html", "_blank");
    },
    handleEdit (id) {
      this.$router.push('/post/publish/' + id);
    },
    handleDelete (id) {
      this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        this.deleteArticle(id);
      }).catch(err => {
        console.log(err);
      });
    },
    handleTrash (id) {
      this.$confirm('此操作会将该文章移到回收站，可在回收站恢复，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        this.trashArticle(id);
      }).catch(err => {
        console.log(err);
      });
    },
    initArticleDatas (articles) {
      this.articleDatas = [];
      for (let key in articles) {
        let item = articles[key];
        const article = {
          id: item.id,
          title: item.title + item.status,
          publish: this.$dayjs(item.created).format('YYYY-MM-DD HH:mm:ss'),
          modified: this.$dayjs(item.modified).format('YYYY-MM-DD HH:mm:ss'),
          category: item.category || this.$util.STATIC.DEFAULT_CATEGORY,
          tags:item.tags || this.$util.STATIC.DEFAULT_TAG,
          typeText:this.$util.typeToString(item.type),
          status: item.status,
          statusText: item.status === this.$util.STATIC.STATUS_TRASH?
            '回收站':
            (this.$util.STATIC.STATUS_DRAFT?'草稿':'已发布'),
          showDelete:item.status !== this.$util.STATIC.STATUS_TRASH,
          showTrash:item.status === this.$util.STATIC.STATUS_TRASH
        };
        // console.log(article);
        this.articleDatas.push(article);
      }
    },
    deleteArticle (id) {
      this.$api.article.deleteArticle({
        postId:id
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.init(this.$route.query.page);
      });
    },
    trashArticle(id){
      this.$api.article.trashArticle({
        postId:id
      }).then(()=>{
        this.$message({
          type: 'success',
          message: '移到回收站成功!'
        });
        this.init(this.$route.query.page);
      });
    },
    init (page) {
      this.$api.article.getArticles({
        pageNum: page || 1,
        pageSize: 10,
        search: this.s
      }).then(data => {
        this.initArticleDatas(data.data.list);
        this.total = data.data.total;
        this.pageSize = data.data.pageSize;
        this.currentPage = Number(page) || 1;
      });
    }
  },
  mounted () {
    this.init(this.$route.query.page);
  }
};
</script>

<style>
.el-table ::-webkit-scrollbar {
  display: block;
  height: 10px;
}
.el-table ::-webkit-scrollbar-thumb {
  background-color: #324157;
}
.el-table ::-webkit-scrollbar-thumb:active {
  background-color: #00aff0;
}
@media screen and (min-width: 600px) {
  .el-table ::-webkit-scrollbar {
    display: block;
    height: 10px;
  }
}
@media screen and (max-width: 600px) {
  .el-table ::-webkit-scrollbar {
    display: none;
  }
}
</style>

<style scoped>
.top-search {
  margin-bottom: 20px;
}
.el-table {
  border: 1px solid #e6ebf5;
}
.admin-page {
  margin-top: 30px;
  text-align: center;
}
</style>
