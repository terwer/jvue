<template>
  <b-row id="body">
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
    <b-col sm="12" md="12" lg="12" xl="8">
      <b-row>
        <b-col sm="12" md="12" lg="12" xl="8">
          <PostList />
        </b-col>
        <b-col id="aside" sm="0" md="0" lg="0" xl="4">
          <Aside />
        </b-col>
      </b-row>
    </b-col>
    <b-col sm="0" md="0" lg="0" xl="2"></b-col>
  </b-row>
</template>

<script>
import PostList from "./PostList.vue";
import Aside from "./Aside.vue";

export default {
  name: "Body",
  components: {
    PostList,
    Aside
  }
};
</script>

<style scoped>
#body {
  padding-top: 10px;
}
/*
  ##Device = Mobile
  ##Screen = B/w 320px - 767px
*/
@media (min-width: 320px) and (max-width: 767px) {
  #aside {
    display: none;
  }
}
/*
##Device = iPad
##Screen = B/w 768px - 1023px
*/
@media (min-width: 768px) and (max-width: 1023px) {
  #aside {
    display: none;
  }
}
/*
  ##Device = iPad Pro
  ##Screen = B/w 1024px - 1365px
*/
@media (min-width: 1024px) and (max-width: 1365px) {
  #aside {
    display: none;
  }
}
</style>
