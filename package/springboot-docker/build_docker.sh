#!/usr/bin/env bash 

cd ../../eyulingo

./mvnw clean package -DskipTests

mv target/demo-0.0.1-SNAPSHOT.jar ../dockerize/springboot-docker/app.jar

cd ../dockerize/sb-docker

docker build -t yuxiqian/eyulingo-server .