<template>
  <div id="postList">
    postList=>
    {{ postList }}
  </div>
</template>

<script>
export default {
  name: "PostList",
  props: {
    postList: {
      type: Array,
      default: () => []
    }
  }
};
</script>

<style scoped></style>
