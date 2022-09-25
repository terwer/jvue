#!/bin/sh

# sudo n 14

cd jvue-server
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev -DskipTests &

cd ../jvue-front
yarn dev