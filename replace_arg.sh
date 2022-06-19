#!/bin/bash

replaceArg() {
  filename="application-rds.properties"
  search=$1
  replace=$2
  if [[ $search != "" && $replace != "" ]]; then
    # mac
    sed -i "_bak" "s/$search/$replace/gi" $filename
    # linux
    # sed -i "s/$search/$replace/gi" $filename
  fi
}

cd ./jvue-server/src/main/resources
pwd

replaceArg '${MYSQL_HOST}' "localhost"
replaceArg '${MYSQL_PORT}' "3306"
replaceArg '${MYSQL_DB_NAME}' "bugucms4"
replaceArg '${MYSQL_USERNAME}' "terwer"
replaceArg '${MYSQL_PASSWORD}' "123456"

echo "done"


