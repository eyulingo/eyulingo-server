#!/usr/bin/env bash 

cp ../eyulingo/src/main/properties_release/application.properties ../eyulingo/src/main/resources/application.properties

cd sql-docker
./build_docker.sh

cd ../

cd springboot-docker
./build_docker.sh

cd ../

docker push yuxiqian/eyulingo-mysql
docker push yuxiqian/eyulingo-server

cp ../eyulingo/src/main/properties_debug/application.properties ../eyulingo/src/main/resources/application.properties
