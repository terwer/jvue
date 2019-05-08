Not build oracle due to permissions with following

```bash
jvue-oracle:
image: jvue/oracle:oracle-xe-11g
container_name: jvue-oracle
build:
  context: ./
  dockerfile: ./jvue-oracle/Dockerfile
ports:
  - "1521:1521"
  - "5500:5500"
volumes:
  - ./data/oracle:/u01/app/oracle
restart: always
```