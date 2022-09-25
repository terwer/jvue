// eslint-disable-next-line no-use-before-define,no-var
var _hmt = _hmt || [];
(function() {
  const hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?8cf152054e4b32b33cbc68fef515bbb5";
  hm.id = "baidu_tj";
  const s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();

console.log("Register baidutongji success");

export default ({ app: { router }, store }) => {
  router.afterEach((to, from) => {
    try {
      window._hmt = window._hmt || [];
      window._hmt.push(["_trackPageview", to.fullPath]);
      console.log("百度统计上报，to=>", to);
    } catch (e) {}
  });
};
