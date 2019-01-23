<p align="center">
    <a href="https://vuejs.org" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="https://vuejs.org/images/logo.png" alt="Vue logo"></a>  
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank" rel="noopener noreferrer"><img width="100" height="117" src="http://www.oracle.com/us/technologies/java/gimmejava/i-code-java-100x117-1705302.png" alt="Java logo"></a>
</p>

<p align="center">
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/npm/v/vue.svg" alt="Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/npm/l/vue.svg" alt="License"></a>
</p>

# next
Next light-weight,responsive project With Vue and Java Nashorn Script engine

# build
1、Go to [webapp](src/main/webapp) and run vue ssr build

```
cd src/main/webapp

npm run ssr:build
```

notice:You can run ``ssr:build-dev`` in development mode to show error logs

2、Run java mavem build

```
mvn clean package -DskipTests
```

3、Copy ``target/ROOT.war`` to ``${TOMCAT_HOME}/webapps``