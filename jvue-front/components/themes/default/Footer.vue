<template>
  <el-row>
    <el-col :xs="24" :md="4">
      <div class="grid-content bg-purple">Footer Left</div>
    </el-col>
    <el-col :xs="24" :md="16">
      <div class="grid-content bg-purple-light">Footer</div>
    </el-col>
    <el-col :xs="24" :md="4">
      <div class="grid-content bg-purple">Footer Right</div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Footer"
};
</script>

<style scoped lang="scss">
.el-row {
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
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  background-color: #f9fafc;
}
</style>
