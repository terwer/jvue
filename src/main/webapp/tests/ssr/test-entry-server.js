import "../../templates/entry-server";

const context = {
  url: "/about"
};

const promise = global.renderServer(context);
console.log("promise=>", promise);

promise.then(
  resolve => {
    console.log("resolve>>", JSON.stringify(resolve));
  },
  rejected => {
    console.log("rejected>>" + JSON.stringify(rejected));
  }
);
