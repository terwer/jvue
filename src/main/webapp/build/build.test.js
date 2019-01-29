/**
 * @file
 *  - 项目构建
 * @author jamesjianpeng <jamesjianpeng@gmail.com>
 */
const exec = require("child_process").exec;
const chalk = require("chalk");

const options = { encoding: "utf8", maxBuffer: 1024 * 500 };

// build ssr_server
const DEVELOPMENT_BUILD_SSR_SERVER =
  "webpack --progress --mode development --renderMode ssr_server";
const DEVELOPMENT_BUILD_SSR_SERVER_TEXT = "ssr_server development打包成功";

// run test script
const TEST_SCRIPT = "babel-node ./src/ssr/server.test.js";
const BUILD_SUCCESS_TEXT = "ssr server development test successful";

const build = function() {
  console.log(chalk.yellow("mode:development"));
  // 构建ssr服务端
  console.log("build ssr_server is starting");
  exec(DEVELOPMENT_BUILD_SSR_SERVER, options, err => {
    if (err) {
      console.log(err);
      return;
    }
    console.log(chalk.blue(DEVELOPMENT_BUILD_SSR_SERVER_TEXT));

    // 拷贝ssr客户端到ssr服务端目录
    console.log("server test is starting");
    exec(TEST_SCRIPT, err => {
      if(err){
        console.log(err)
      }
      console.log(chalk.green(BUILD_SUCCESS_TEXT) + "🌟 ");
    });
  });
};

build();
