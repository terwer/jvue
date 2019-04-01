# 构建镜像
# docker build . -t jvue-mysql:5.7.24 --force-rm --no-cache

# 运行容器
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 jvue-mysql:5.7.24
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d jvue-mysql:5.7.24

# 进入容器
# docker exec -it b8427ee0f74a bash

# 登录容器MySQL
# docker exec -it jvue-mysql mysql -uroot -p123456
# show variables like 'character%';
# select now();

#依赖的镜像
FROM mysql/mysql-server:5.7.24
#镜像创建者的信息
MAINTAINER terwer "cbgtyw@gmail.com"
# 设置编码
ENV LANG C.UTF-8
# 设置时区
# ENV TZ Asia/Shanghai
ENV TZ CST-8