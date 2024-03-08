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
  <a href="https://nodejs.org/"><img src="https://img.shields.io/badge/node-v14.20.0-green.svg" alt="Node Version"></a>
  <a href="https://www.npmjs.com/"><img src="https://img.shields.io/badge/npm-v6.14.7-blue.svg" alt="npm Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/badge/vue-2.6.6-brightgreen.svg" alt="Vue version"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# Introduction

## jvue
Next light-weight,responsive project
With Docker,Vue2,Vue CLI 3,webpack4,Java8 and Spring Boot5

## Important Changes in JVue 4

Using [Docker](https://docs.docker.com/develop/dev-best-practices/) for deploy

## Install
The instructions assume that you have already installed [Docker](https://docs.docker.com/installation/) and [Docker Compose](https://docs.docker.com/compose/install/). 

## Run

### install docker
```
yum install docker
```

### install docker-compose
```
curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```

See [https://docs.docker.com/compose/reference/up/](https://docs.docker.com/compose/reference/up/)

## Run jvue

### docker(for dev)

```bash
docker compose up --build
```

### docker(for production)

```bash
docker compose up -d --build
```

### dev

1. jvue-mysql
```bash
docker start jvue-mysql
```

2. jvue-server

```bash
cd ./jvue-server/src/main/java/com/terwergreen/jvueserver
# then run JVueServerApplication `main` function
# that's all
```

3. jvue-front

```bash
cd ./jvue-front
yarn config set registry https://registry.npmmirror.com/ --global  && \
    yarn config set disturl https://npmmirror.com/package/dist --global && \
    yarn config set sass_binary_site https://cdn.npmmirror.com/binaries/node-sass --global  && \
    yarn config set electron_mirror https://registry.npmmirror.com/binary.html?path=electron/ --global  && \
    yarn config set puppeteer_download_host https://registry.npmmirror.com/binary.html --global  && \
    yarn config set chromedriver_cdnurl https://cdn.npmmirror.com/binaries/chromedriver --global  && \
    yarn config set operadriver_cdnurl https://cdn.npmmirror.com/binaries/operadriver --global  && \
    yarn config set phantomjs_cdnurl https://cdn.npmmirror.com/binaries/phantomjs --global  && \
    yarn config set selenium_cdnurl https://cdn.npmmirror.com/binaries/selenium --global  && \
    yarn config set node_inspector_cdnurl https://cdn.npmmirror.com/binaries/node-inspector --global
yarn
yarn dev
```
or simplely dev front project without step2

```bash
./dev.sh
```

# Structure

It is a [docker-compose](https://docs.docker.com/compose) project        
Have fun and enjoy!

# Contribute

You can contribute simplely by create a pull request for me

For detailed explanation on how things work, please visit [author's blog](https://terwer.space).