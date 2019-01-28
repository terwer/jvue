/**
 * @file
 *  - 项目构建
 * @author jamesjianpeng <jamesjianpeng@gmail.com>
 */
const exec = require("child_process").exec;
const chalk = require("chalk");

const encoding = { encoding: "utf8" };

// build ssr_client
const PRODUCTION_BUILD_SSR_CLIENT =
  "webpack --progress --mode production --renderMode ssr_client";
const PRODUCTION_BUILD_SSR_CLIENT_TEXT = "ssr_client production打包成功";

// build ssr_server
const PRODUCTION_BUILD_SSR_SERVER =
  "webpack --progress --mode production --renderMode ssr_server";
const PRODUCTION_BUILD_SSR_SERVER_TEXT = "ssr_server production打包成功";

// copy js from ssrclientdist to ssrdist
const COPY_SCRIPT = "cp ssrclientdist/js/* ssrdist/js && rm -rf ssrclientdist";
const COPY_SCRIPT_TEXT = "copy js from ssrclientdist to ssrdist successful";

const build = function() {
  // 构建ssr客户端
  console.log("build ssr_client");
  exec(PRODUCTION_BUILD_SSR_CLIENT, encoding, err => {
    if (err) {
      console.log(err);
      return;
    }
    console.log(chalk.blue(PRODUCTION_BUILD_SSR_CLIENT_TEXT));

    // 构建ssr服务端
    console.log("build ssr_server");
    exec(PRODUCTION_BUILD_SSR_SERVER, encoding, err => {
      if (err) {
        console.log(err);
        return;
      }
      console.log(chalk.blue(PRODUCTION_BUILD_SSR_SERVER_TEXT));

      // 拷贝ssr客户端到ssr服务端目录
      console.log("copy script");
      exec(COPY_SCRIPT, encoding, () => {
        console.log(chalk.green(COPY_SCRIPT_TEXT) + "🌟 ");
      });
    });
  });
};

build();
