/**
 * 判断是否是Nashorn环境
 * @returns {boolean}
 */
var isInNashorn = function() {
  const isInNashorn =
    typeof document === "undefined" || typeof window === "undefined";
  console.log("isInNashorn:" + isInNashorn);
  return isInNashorn;
};
var inBrowser =
  typeof window !== "undefined" && typeof document !== "undefined";

export { isInNashorn, inBrowser };
