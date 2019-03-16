<template>
  <el-form
    ref="form"
    label-position="left"
    :rules="rules"
    :model="form"
    label-width="80px"
  >
    <el-form-item label="文章标题">
      <el-input v-model="form.postTitle"></el-input>
    </el-form-item>
    <el-form-item label="别名">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="文章类型">
      <el-select v-model="form.region" placeholder="请选择文章类型">
        <el-option label="文章" value="post"></el-option>
        <el-option label="随笔" value="essay"></el-option>
        <el-option label="页面" value="page"></el-option>
        <el-option label="笔记" value="note"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="发布时间">
      <el-date-picker
        type="datetime"
        placeholder="选择日期"
        v-model="form.date1"
        style="width: 100%;"
      ></el-date-picker>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')"
        >立即创建</el-button
      >
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "PostEdit",
  data() {
    return {
      form: {
        postTitle: ""
      },
      rules: {
        postTitle: [
          { required: true, message: "请输入文章标题", trigger: "blur" },
          { min: 3, max: 20, message: "长度在 3 到 20 个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
