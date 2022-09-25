# 依赖的镜像
FROM node:14-alpine

# 工作目录
WORKDIR /app

# 安装依赖
COPY ./jvue-front/package.json ./

# yarn 已经有了，没必要重复安装
RUN yarn config set registry https://registry.npmmirror.com/ --global  && \
    yarn config set disturl https://npmmirror.com/package/dist --global && \
    yarn config set sass_binary_site https://cdn.npmmirror.com/binaries/node-sass --global  && \
    yarn config set electron_mirror https://registry.npmmirror.com/binary.html?path=electron/ --global  && \
    yarn config set puppeteer_download_host https://registry.npmmirror.com/binary.html --global  && \
    yarn config set chromedriver_cdnurl https://cdn.npmmirror.com/binaries/chromedriver --global  && \
    yarn config set operadriver_cdnurl https://cdn.npmmirror.com/binaries/operadriver --global  && \
    yarn config set phantomjs_cdnurl https://cdn.npmmirror.com/binaries/phantomjs --global  && \
    yarn config set selenium_cdnurl https://cdn.npmmirror.com/binaries/selenium --global  && \
    yarn config set node_inspector_cdnurl https://cdn.npmmirror.com/binaries/node-inspector --global

RUN yarn

RUN yarn build

COPY ./jvue-front .

# 注意这个一定要要在yarn后面，否则devDependencies的包无法安装
# ENV HOST 0.0.0.0

CMD ["yarn", "start"]
