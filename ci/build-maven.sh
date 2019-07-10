#!/usr/bin/env bash 

echo "Starting eyulingo-android Unit Tests"

# Put unit test code here
# 

./eyulingo/mvnw clean compile package install -DskipTests=true -Dmaven.javadoc.skip=true -Dgpg.skip=true

./eyulingo/mvnw test

echo "eyulingo-server Unit Tests complete!"
