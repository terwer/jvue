/**
 * @file
 *  - 项目构建
 * @author jamesjianpeng <jamesjianpeng@gmail.com>
 */
const exec = require("child_process").exec;
const chalk = require("chalk");

const options = { encoding: "utf8", maxBuffer: 1024 * 500 };

// build ssr_client
const DEVELOPMENT_BUILD_SSR_CLIENT =
  "webpack --progress --mode production --renderMode ssr_client";
const DEVELOPMENT_BUILD_SSR_CLIENT_TEXT = "ssr_client production打包成功";

// build ssr_server
const DEVELOPMENT_BUILD_SSR_SERVER =
  "webpack --progress --mode production --renderMode ssr_server";
const DEVELOPMENT_BUILD_SSR_SERVER_TEXT = "ssr_server production打包成功";

// copy js from ssrclientdist to ssrdist
const COPY_SCRIPT = "cp ssrclientdist/js/* ssrdist/js && rm -rf ssrclientdist";
const BUILD_SUCCESS_TEXT = "ssr build production successful";

const build = function() {
  console.log(chalk.yellow("mode:production"));
  // 构建ssr客户端
  console.log("build ssr_client is starting");
  exec(DEVELOPMENT_BUILD_SSR_CLIENT, options, err => {
    if (err) {
      console.log(err);
      return;
    }
    console.log(chalk.blue(DEVELOPMENT_BUILD_SSR_CLIENT_TEXT));

    // 构建ssr服务端
    console.log("build ssr_server is starting");
    exec(DEVELOPMENT_BUILD_SSR_SERVER, options, err => {
      if (err) {
        console.log(err);
        return;
      }
      console.log(chalk.blue(DEVELOPMENT_BUILD_SSR_SERVER_TEXT));

      // 拷贝ssr客户端到ssr服务端目录
      console.log("copy script is starting");
      exec(COPY_SCRIPT, () => {
        console.log(chalk.green(BUILD_SUCCESS_TEXT) + "🌟 ");
      });
    });
  });
};

build();
