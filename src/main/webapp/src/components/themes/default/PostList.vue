<template>
  <div id="postList">
    <b-card
      title="图片文章"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-left
      tag="article"
    >
      <p class="card-text">
        Some quick example text to build on the card title and make up the bulk
        of the card's content.
      </p>
      <b-button href="#" variant="primary">Go somewhere</b-button>
    </b-card>
    <b-card title="文字文章" sub-title="Card subtitle">
      <p class="card-text">
        Some quick example text to build on the <em>card title</em> and make up
        the bulk of the card's content.
      </p>
      <a href="#" class="card-link">Card link</a>
      <b-link href="#" class="card-link">Another link</b-link>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "PostList"
};
</script>

<style scoped>
#postList {
}

#postList .card {
  margin: 10px 0;
}
</style>
