#!/usr/bin/env bash 

mv ../eyulingo/src/main/resources/application.properties ../eyulingo/src/main/resources/application.properties.disabled
mv ../eyulingo/src/main/resources/application_release.properties.disabled ../eyulingo/src/main/resources/application_release.properties
cd sql-docker
./build_docker.sh

cd ../

cd sb-docker
./build_docker.sh

cd ../

docker push yuxiqian/bookie-mysql
docker push yuxiqian/bookie-server

docker-compose up

mv ../eyulingo/src/main/resources/application.properties.disabled ../eyulingo/src/main/resources/application.properties
mv ../eyulingo/src/main/resources/application_release.properties ../eyulingo/src/main/resources/application_release.properties.disabled