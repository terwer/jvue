<template>
  <el-row>
    <el-col :xs="24" :sm="24" :md="6">
      <div class="grid-content bg-purple-green">&nbsp;</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12">
      <div class="grid-content bg-purple-light">Header</div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="6">
      <div class="grid-content bg-purple-green">&nbsp;</div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style scoped lang="scss">
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-green {
  height: auto;
  background: #44f21e;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  background-color: #f9fafc;
}
</style>
