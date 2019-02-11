import axios from "axios";

console.log("api process.env.NODE_ENV:", process.env.NODE_ENV);
const baseUrl =
  // process.env.NODE_ENV === "production"
  // ? "http://www.terwergreen.com/api/":
  "http://127.0.0.1:8081/api/";
console.log("baseUrl:", baseUrl);

const http = axios.create({
  baseURL: baseUrl,
  timeout: 10000
});

// Make a request for a user with a given ID
function getPostList() {
  const GET_POST_LIST = "blog/post/list";
  console.log("GET_POST_LIST", GET_POST_LIST);
  return http.post(GET_POST_LIST);
}

const api = {
  getPostList: getPostList
};

export default api;
