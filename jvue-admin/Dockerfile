#依赖的镜像
FROM node:10.15.1-alpine

# CDN注册
RUN npm config set registry https://registry.npm.taobao.org && \
    npm set chromedriver_cdnurl https://npm.taobao.org/mirrors/chromedriver && \
    npm cache clean --force

# 工作目录
WORKDIR /app

# 安装依赖
COPY ./jvue-admin/package.json ./
RUN npm install

# 注意这个一定要要在npm install后面，否则devDependencies的包无法安装
ENV HOST 0.0.0.0

COPY ./jvue-admin .

CMD ["npm", "run", "serve"]