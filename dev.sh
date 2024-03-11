#!/bin/sh

# 终止占用8008端口的进程，这里还要考虑必须是 java 进程
# lsof -ti:8008 | xargs kill -9

# 终止占用3000端口的进程，这里还要考虑必须是 node 进程
# lsof -ti:3000 | xargs kill -9

# sudo n 18

cd jvue-server
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev -DskipTests &

cd ../jvue-front
ARTALK_SERVER_URL=https://v4.terwergreen.com:8003
yarn dev

# 需要考虑优雅退出