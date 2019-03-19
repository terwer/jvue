<template>
  <el-row>
    <el-col :xs="24" :md="4">
      <div class="grid-content bg-purple-light">Info</div>
    </el-col>
    <el-col :xs="24" :md="20">
      <div class="grid-content bg-purple-purple">FriendLink</div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "FriendLink"
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
.bg-purple-light {
  background: #e5e9f2;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-purple {
  background: #ad51f2;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  background-color: #f9fafc;
}
</style>
