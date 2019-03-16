#依赖的镜像
FROM node:10.15.1-alpine


# 工作目录
WORKDIR /app

# 安装依赖
COPY ./jvue-front/package.json ./

# 因为被墙，单独安装
ENV SASS_BINARY_SITE https://npm.taobao.org/mirrors/node-sass
# CDN注册
RUN npm config set registry https://registry.npm.taobao.org && \
    npm set chromedriver_cdnurl https://npm.taobao.org/mirrors/chromedriver && \
    npm cache clean --force && \
    npm install node-sass && npm install

COPY ./jvue-front/build/build.prod.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# 注意这个一定要要在npm install后面，否则devDependencies的包无法安装
ENV HOST 0.0.0.0

COPY ./jvue-front .

ENTRYPOINT [ "/entrypoint.sh" ]

CMD ["npm", "run", "prod-start"]