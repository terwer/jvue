// sudo npm install -g @babel/core @babel/cli @babel/node @babel/preset-env @babel/preset-flow
// babel test.js --presets=@babel/preset-env,@babel/flow
// babel-node test.js --presets=@babel/preset-env,@babel/flow

(async () => {
  const context = {
    url: "/about"
  };
  const promise = global.renderServer(context);
  console.log("promise=>", promise);
  return promise;
})();
