#依赖的镜像
FROM node:10.15.1-alpine
#镜像创建者的信息
MAINTAINER terwer "csyouwei@gmail.com"
# 设置编码
ENV LANG C.UTF-8
# 设置Node环境
ENV HOST 0.0.0.0
ENV NODE_ENV=production

WORKDIR /app

COPY ./jvue-front .

RUN npm config set registry https://registry.npm.taobao.org && \
    npm i -g @vue/cli \
    npm install && \
    npm run build

CMD ["npm","run","start"]