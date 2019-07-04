#!/usr/bin/env bash 

mv ../eyulingo/src/main/resources/application.properties ../eyulingo/src/main/resources/application.properties.disabled
mv ../eyulingo/src/main/resources/application_release.properties.disabled ../eyulingo/src/main/resources/application_release.properties
cd sql-docker
./build_docker.sh

cd ../

cd springboot-docker
./build_docker.sh

cd ../

docker push yuxiqian/eyulingo-mysql
docker push yuxiqian/eyulingo-server

docker-compose up

mv ../eyulingo/src/main/resources/application.properties.disabled ../eyulingo/src/main/resources/application.properties
mv ../eyulingo/src/main/resources/application_release.properties ../eyulingo/src/main/resources/application_release.properties.disabled