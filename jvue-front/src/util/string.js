/**
 * string.js
 *
 * @author Terwer
 * @version 1.0
 * 19-2-28 上午12:23
 **/
const isEmptyOrUndefined = v => {
  return (
    typeof v == "undefined" ||
    v == null ||
    (typeof v == "string" && (v === "" || v.trim() === ""))
  );
};

export { isEmptyOrUndefined };
