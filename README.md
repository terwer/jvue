<p align="center">
    <a href="https://vuejs.org" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="slogan/vue.png" alt="Vue logo"></a>  
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank" rel="noopener noreferrer"><img width="200" height="117" src="slogan/java.png" alt="Java logo"></a>
</p>

<p align="center">
  <a href="https://docs.docker.com/develop/dev-best-practices/"><img src="https://img.shields.io/badge/docker-18.09.2-blue.svg" alt="Docker version"></a>
  <a href="https://docs.docker.com/compose"><img src="https://img.shields.io/badge/docker_compose-1.23.2-brightgreen.svg" alt="Docker-compose version"></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img src="https://img.shields.io/badge/jdk-1.8.0_191-orange.svg" alt="Java logo"></a>
  <a href="http://maven.apache.org/"><img src="https://img.shields.io/badge/maven-3.6.0-blue.svg" alt="Maven logo"></a>
  <a href="https://nodejs.org/"><img src="https://img.shields.io/badge/node-v10.15.3-green.svg" alt="Node Version"></a>
  <a href="https://www.npmjs.com/"><img src="https://img.shields.io/badge/npm-v6.4.1-blue.svg" alt="npm Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/badge/vue-2.6.6-brightgreen.svg" alt="Vue version"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# Introduction

## jvue
Next light-weight,responsive project
With Docker,Vue,Vue CLI 3,webpack,Java and Spring Boot

# Important Changes in JVue 4

Using [Docker](https://docs.docker.com/develop/dev-best-practices/) for deploy

# Install
The instructions assume that you have already installed [Docker](https://docs.docker.com/installation/) and [Docker Compose](https://docs.docker.com/compose/install/). 

# Run

## install docker
```
yum install docker
```

## install docker-compose
```
curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```

See [https://docs.docker.com/compose/reference/up/](https://docs.docker.com/compose/reference/up/)

## run jvue

### via docker(for production)
```bash
docker compose up -d --build
```

### custom(for dev)
1. mysql

   ```bash
   docker compose -f docker-compose-mysql.yml
   ```

2. Jvue-server

   ```bash
   cd /Users/terwer/Documents/myworks/jvue/jvue-server/src/main/java/com/terwergreen/jvueserver
   # then run JVueServerApplication `main` function
   # that's all
   ```

3. Jvue-front

   ```bash
   cd /Users/terwer/Documents/myworks/jvue/jvue-front
   yarn
   yarn dev
   ```

# Structure

It is a [docker-compose](https://docs.docker.com/compose) project        
Have fun and enjoy!

# Contribute

You can contribute simplely by create a pull request for me

For detailed explanation on how things work, please visit [author's blog](https://terwergreen.com).