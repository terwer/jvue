<p align="center">
    <a href="https://vuejs.org" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="slogan/vue.png" alt="Vue logo"></a>  
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="slogan/java.png" alt="Java logo"></a>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://github.com/eclipsesource/J2V8" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="slogan/j2v8.png" alt="J2V8 logo"></a>
</p>

<p align="center">
  <a href="https://nodejs.org/"><img src="https://img.shields.io/badge/node-v10.15.1-green.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/"><img src="https://img.shields.io/badge/npm-v6.4.1-blue.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/badge/vue-2.6.6-brightgreen.svg" alt="Version"></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img src="https://img.shields.io/badge/jdk-1.8.0_191-orange.svg" alt="Java logo"></a>
  <a href="http://maven.apache.org/"><img src="https://img.shields.io/badge/maven-3.6.0-blue.svg" alt="Maven logo"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# Introduction
## jvue
Next light-weight,responsive project
With Vue,webpack,Spring Boot and eclipse j2v8 Script engine for server-side-rendering

## Note !!!

> j2v8 is not support promise on windows right now,
> so ``linux`` is prefered for production,windows support is on the way ...

# Build setup
1、Go to [webapp](src/main/webapp) and run vue ssr build

```bash
cd src/main/webapp && yarn && yarn build-ssr
```

# compile && run test

```bash
mvn -v && mvn compile && mvn exec:java
```

notice:You can run ``yarn build-ssr-dev`` in development mode to show error logs

2、Run java mavem build

```bash
mvn clean package -DskipTests
```

3、Copy ``target/ROOT.war`` to ``${TOMCAT_HOME}/webapps``

or

```bash
mvn clean spring-boot:run
```

# Structure

The whole project is a ``Java Spring Boot Maven`` structure,the ``src/main/webapp`` is a complete ``Vue`` Project With ``webpack`` structure

When build finish,all files merged into ``target/ROOT.war``

Have fun and enjoy!

# Contribute

You can contribute simplely by create a pull request for me

For detailed explanation on how things work, please visit [author's blog](http://www.terwergreen.com).