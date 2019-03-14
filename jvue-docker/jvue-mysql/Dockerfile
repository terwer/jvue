# 构建镜像
# docker build . -t jvue-mysql:5.7.24  --force-rm --no-cache

# 运行容器
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 jvue-mysql:5.7.24
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d jvue-mysql:5.7.24

# 登录容器MySQL
# docker exec -it jvue-mysql mysql -uroot -p123456
# show variables like 'character%';
# select now();

#依赖的镜像
FROM mysql/mysql-server:5.7.24
#镜像创建者的信息
MAINTAINER terwer "csyouwei@gmail.com"
# 设置编码
ENV LANG C.UTF-8
# 设置时区
ENV TZ Asia/Shanghai
#容器运行时启动的命令，下面命令等价于 CMD echo 'TZ is:'$TZ
RUN echo 'LANG is:'$LANG',TZ is:'$TZ