#!/usr/bin/env bash 

cd ../../eyulingo-admin

./mvnw clean package -DskipTests

mv target/eyulingo-admin-0.0.1-SNAPSHOT.jar ../package/admin-docker/app.jar

cd ../package/admin-docker

docker build -t yuxiqian/eyulingo-admin .