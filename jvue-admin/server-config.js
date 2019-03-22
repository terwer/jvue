const isProd = process.env.NODE_ENV === "production";
const localhost = "http://127.0.0.1:8081/";
// build的时候传入参数或者使用localhost
const baseUrl = "http://www.terwergreen.com/";
const api = isProd ? baseUrl : localhost;
const tokenString =
  "r6o-glNUVnFxMyEUBHGK9i4vriKCfpvnHXv0Rsc6zx2ZWtLArXsAF6mE36ZrLqCbrT95cnrOD_TM0-qOIkcLBQ";
export default {
  isProd,
  api,
  tokenString
};
