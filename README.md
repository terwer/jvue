<p align="center">
    <a href="https://vuejs.org" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="https://vuejs.org/images/logo.png" alt="Vue logo"></a>  
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="http://www.oracle.com/us/technologies/java/gimmejava/i-code-java-100x117-1705302.png" alt="Java logo"></a>
</p>

<p align="center">
  <a href="https://nodejs.org/"><img src="https://img.shields.io/badge/node-v10.15.0-green.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/"><img src="https://img.shields.io/badge/npm-v6.6.0-blue.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/badge/vue-2.5.22-brightgreen.svg" alt="Version"></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img src="https://img.shields.io/badge/jdk-1.8.0_181-orange.svg" alt="Java logo"></a>
  <a href="http://maven.apache.org/"><img src="https://img.shields.io/badge/maven-3.5.4-blue.svg" alt="Maven logo"></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# Introduction
## jvue
Next light-weight,responsive project
With Vue,Spring Boot and Java Nashorn Script engine for server-side-rendering

# Build setup
1、Go to [webapp](src/main/webapp) and run vue ssr build

```
cd src/main/webapp && yarn && yarn run ssr:build
```

notice:You can run ``ssr:build-dev`` in development mode to show error logs

2、Run java mavem build

```
mvn clean package -DskipTests
```

3、Copy ``target/ROOT.war`` to ``${TOMCAT_HOME}/webapps``

# Structure

The whole project is a Java Spring Boot Maven structure,the ``src/main/webapp`` is a complete Vue Project With webpack structure

When build finish,all files merged into ``target/ROOT.war``

Have fun and enjoy!

# Contribute

You can contribute simplely by create a pull request for me

For detailed explanation on how things work, please visit [author's blog](https://www.terwergreen.com).