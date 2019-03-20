<template>
  <div id="aside">
    aside=>
    {{ postList }}
  </div>
</template>
<script>
import { getLogger } from "../../../util/logger";
const logger = getLogger("components/themes/default/Aside");

export default {
  name: "Aside",
  data() {
    return {
      postList: []
    };
  },
  async mounted() {
    const postsResult = await this.$axios.$post("/blog/post/list", {
      isHot: 1
    });
    this.postList = postsResult.status === 1 ? postsResult.data.list || [] : [];
    logger.info("fetch aside postList finish");
  }
};
</script>

<style scoped></style>
