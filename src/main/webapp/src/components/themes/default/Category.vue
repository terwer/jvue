<template>
  <b-container fluid> This is category {{ catId }} </b-container>
</template>

<script>
export default {
  name: "Category",
  computed: {
    catId() {
      return this.$route.params.id;
    }
  }
};
</script>

<style scoped></style>
