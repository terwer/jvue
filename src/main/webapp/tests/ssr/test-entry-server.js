import "../../build/ssrdist/entry-server";

const context = {
  url: "/"
};

const promise = global.renderServer(context);
console.log("promise=>", promise);

promise.then(
  resolve => {
    console.log("resolve>>", resolve);
  },
  rejected => {
    console.log("rejected>>" + rejected);
  }
);
