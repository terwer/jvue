<template>
  <div>
    <el-table :data="articleDatas" border stripe style="width: 100%">
      <el-table-column prop="id" label="id" width="60"></el-table-column>
      <el-table-column
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
        prop="statusText"
        label="状态"
        width="100"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="publish"
        label="发布日期"
        width="150"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="modified"
        label="修改日期"
        width="150"
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
export default {
  data: function () {
    return {
      articleDatas: [],
      total: 0,
      pageSize: 10,
      currentPage: 1
    };
  },
  methods: {
    handlePreview(id){
      window.open("http://www.terwergreen.com/post-dark/" + id + ".html", "_blank");
    },
    handleEdit (id) {
      this.$router.push('/article/publish/' + id);
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
        let data = articles[key];
        let article = {
          id: data.id,
          title: data.title,
          publish: this.$dayjs(data.created).format('YYYY-MM-DD HH:mm'),
          modified: this.$dayjs(data.modified).format('YYYY-MM-DD HH:mm'),
          category: data.category || this.$util.STATIC.DEFAULT_CATEGORY,
          status:data.status,
          statusText:this.$util.STATIC.STATUS_PUBLISH === data.status ? '已发布' : '回收站',
          showTrash:data.status !== this.$util.STATIC.STATUS_PUBLISH,
          showDelete:data.status !== this.$util.STATIC.STATUS_DRAFT
        };
        this.articleDatas.push(article);
      }
    },
    deleteArticle (id) {
      this.$api.article.deleteArticle(id).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.init(this.$route.query.page);
      });
    },
    init (page) {
      this.$api.article.getArticles({
        pageNum: page || 1,
        pageSize: 5
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
.el-table {
  border: 1px solid #e6ebf5;
}
.admin-page {
  margin-top: 30px;
  text-align: center;
}
</style>
