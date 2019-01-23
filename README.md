# next
Next light-weight,responsive project

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