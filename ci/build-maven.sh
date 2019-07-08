#!/usr/bin/env bash 

echo "Starting eyulingo-android Unit Tests"

# Put unit test code here
# 

./eyulingo/mvnw package

./eyulingo/mvnw test

echo "eyulingo-server Unit Tests complete!"
