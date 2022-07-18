<template>
  <div>
    <!-- 自定义评论 -->
    <h2 id="commentArea">评论</h2>
    <div id="Comments"></div>
  </div>
</template>

<script>
import "artalk/dist/Artalk.css";
// import Artalk from "artalk";

export default {
  mounted() {
    if (typeof window !== "undefined") {
      // 初始化Artalk
      this.initArtalk();
      // consoel.log("初始化Artalk");
    }
  },
  methods: {
    initArtalk() {
      const ArtalkComponent = () => ({
        // 需要加载的组件 (应该是一个 `Promise` 对象)
        component: import("artalk")
        // 异步组件加载时使用的组件
        // loading: LoadingComponent,
        // 加载失败时使用的组件
        // error: ErrorComponent,
        // 展示加载时组件的延时时间。默认值是 200 (毫秒)
        // delay: 200,
        // 如果提供了超时时间且组件加载也超时了，
        // 则使用加载失败时使用的组件。默认值是：`Infinity`
        // timeout: 3000,
      });

      // console.log("ArtalkComponent()=>", ArtalkComponent());

      ArtalkComponent().component.then(function(result) {
        // console.log("result=>", result.default);
        const Artalk = result.default;
        console.log("Artalk准备初始化...");

        // eslint-disable-next-line no-new
        new Artalk({
          el: "#Comments",
          pageKey: "", // 页面链接
          pageTitle: "", // 页面标题
          server: "https://talk.terwergreen.com", // 后端地址
          site: "浅海拾贝"
        });
        console.log("Artalk初始化完成...");
      });
    }
  }
};
</script>
