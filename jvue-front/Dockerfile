#依赖的镜像
FROM node:10.15.3-alpine


# 工作目录
WORKDIR /app

# 安装依赖
COPY ./jvue-front/package.json ./

# 因为被墙，单独安装
ENV SASS_BINARY_SITE https://npm.taobao.org/mirrors/node-sass
# CDN注册以及安装依赖库
RUN npm config set registry https://registry.npm.taobao.org && \
    npm set chromedriver_cdnurl https://npm.taobao.org/mirrors/chromedriver && \
    npm cache clean --force && \
    npm install node-sass && npm install

COPY ./jvue-front .

# 构建项目
RUN npm run clean && \
npm run dev-build && \
mv dist/index.html dist/index.ssr.html

# 注意这个一定要要在npm install后面，否则devDependencies的包无法安装
ENV HOST 0.0.0.0

CMD ["npm", "run", "dev-start"]