# 构建镜像
# docker build . -t jvue-server:4.0.0 --force-rm --no-cache

# 依赖的镜像
FROM maven:3.6.0-jdk-8-alpine

#镜像创建者的信息
MAINTAINER terwer cbgtyw@gmail.com"

# 设置编码
# ENV LANG C.UTF-8

# 设置时区
ENV TZ Asia/Shanghai
# ENV TZ CST-8
RUN echo "Asia/Shanghai" > /etc/timezone

ENV LANG en_US.UTF-8
RUN apk add --update ttf-dejavu fontconfig && rm -rf /var/cache/apk/*