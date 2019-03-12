<p align="center">
    <a href="https://vuejs.org" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="slogan/vue.png" alt="Vue logo"></a>  
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank" rel="noopener noreferrer"><img width="200" height="117" src="slogan/java.png" alt="Java logo"></a>
</p>

<p align="center">
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img src="https://img.shields.io/badge/jdk-1.8.0_191-orange.svg" alt="Java logo"></a>
  <a href="http://maven.apache.org/"><img src="https://img.shields.io/badge/maven-3.6.0-blue.svg" alt="Maven logo"></a>
  <a href="https://nodejs.org/"><img src="https://img.shields.io/badge/node-v10.15.1-green.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/"><img src="https://img.shields.io/badge/npm-v6.4.1-blue.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/badge/vue-2.6.6-brightgreen.svg" alt="Version"></a>
  <a href="https://gcc.gnu.org/"><img src="https://img.shields.io/badge/gcc-7.3.0-blue.svg" alt="GCC logo"></a>
  <a href="https://gcc.gnu.org/"><img src="https://img.shields.io/badge/g++-7.3.0-blue.svg" alt="G++ logo"></a>
  <a href="https://github.com/jvuesource/J2V8"><img src="https://img.shields.io/badge/j2v8-4.3.0-blue.svg" alt="J2V8 Logo"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# Important Changes in JVue 4

Use Docker for deploy

# Introduction

## jvue
Next light-weight,responsive project
With Docker,Vue,Vue CLI 3,webpack and Spring Boot

# Build setup

## Build Vue

```bash
cd src/main/vue && npm i
./build/build.prod.sh
```

## Go back to root forder

```bash
cd ../../../../jvue
```

## Install j2v8

### linux
```bash
mvn install:install-file -Dfile=libs/j2v8_linux_x86_64-4.8.0.jar
```

or

```bash
mvn install:install-file -Dfile=libs/j2v8_linux_x86_64-4.8.3.jar
```

### windows
```bash
mvn install:install-file -Dfile=libs/j2v8_win32_x86_64-4.8.3.jar -Dpackaging=jar
```

# Run CLI

## linux
```bash
mvn -f pom_linux_x86_x64.xml clean package exec:java
```
### windows
```bash
mvn clean package exec:java
```

## Run Spring Boot

### linux
```bsah
mvn -f pom_linux_x86_x64.xml clean package spring-boot:run
```
### windows
```bsah
mvn clean package spring-boot:run
```

## Start Tomcat

```
# 切换到root用户
su
cd /home/soft/apache-tomcat-9.0.16/bin && ./catalina.sh run
```

# Structure

The whole project is a ``Java Spring Boot Maven`` structure,the ``src/main/vue`` is a complete ``Vue`` Project With ``Vue CLI 3`` structure

When build finish,all files merged into ``target/ROOT.war``

Have fun and enjoy!

# Contribute

You can contribute simplely by create a pull request for me

For detailed explanation on how things work, please visit [author's blog](http://www.terwergreen.com).