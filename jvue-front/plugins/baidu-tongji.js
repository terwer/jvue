// eslint-disable-next-line no-use-before-define,no-var
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?128e63f03f80b29aa4381b8abef35daa";
  var s = document.getElementsByTagName("script")[0];
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
