# 构建镜像
# docker build . -t jvue-mysql:5.7.24

# 运行容器
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 jvue-mysql:5.7.24
# docker run --name=jvue-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d jvue-mysql:5.7.24

# 登录容器MySQL
# docker exec -it jvue-mysql mysql -uroot -p123456
# show variables like 'character%';

FROM mysql/mysql-server:5.7.24

MAINTAINER terwer "csyouwei@gmail.com"

ENV LANG C.UTF-8