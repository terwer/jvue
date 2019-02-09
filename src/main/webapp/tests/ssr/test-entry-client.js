import "../../build/ssrdist/entry-client";

var inBrowser = require("../../src/util/dom").inBrowser;

if (inBrowser) {
  console.log("inBrowser");
  global.renderClient();
} else {
  console.log("not in inBrowser");
}
