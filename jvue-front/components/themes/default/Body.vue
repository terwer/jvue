<template>
  <el-row>
    <el-col :xs="24" :md="16">
      <div class="grid-content bg-purple-dark">Body</div>
    </el-col>
    <el-col :xs="24" :md="8">
      <div class="grid-content bg-purple-blue">Aside</div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Body"
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
.bg-purple-blue {
  background: #2a57f2;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  background-color: #f9fafc;
}
</style>
