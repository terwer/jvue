// 编译api.js
// yarn babel ./tests/test-api.js --presets=@babel/preset-env
// 编译并运行api.js
// yarn babel-node ./tests/test-api.js --presets=@babel/preset-env

import api from "../src/api";

// test getPostList
api
  .getPostList()
  .then(function(response) {
    // handle success
    console.log(response.request.res.responseUrl);
    console.log(response.status);
    console.log(response.data);
  })
  .catch(function(error) {
    // handle error
    console.log(error);
  })
  .then(function() {
    // always executed
    console.log("finished");
  });
