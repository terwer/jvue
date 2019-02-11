/* eslint-disable no-undef,no-unused-vars */
import "../../templates/entry-server";

const context = {
  url: "/"
};

// ================
// promise version
// ================
// const promise = global.renderServer(context);
// console.log("promise=>", promise);
//
// promise.then(
//   resolve => {
//     console.log("resolve>>", JSON.stringify(resolve));
//   },
//   rejected => {
//     console.log("rejected>>" + JSON.stringify(rejected));
//   }
// );

if (typeof renderServer !== "undefined") {
  renderServer(context);
}

// =======================
// callbacks
// =======================
function onServerRenderSuccess(res) {
  console.log("successCallback invoked");
}

function onServerRenderError(res) {
  console.log("errorCallback invoked");
}
