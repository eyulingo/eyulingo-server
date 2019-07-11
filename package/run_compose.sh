#!/usr/bin/env bash

echo "[ ] removing obsolete images ... "
docker images | grep -E "(eyulingo-server|eyulingo-mysql)" | awk '{print $3}' | uniq | xargs -I {} docker rmi --force {}
echo "[*] removed obsolete images"

echo "[ ] stopping docker-compose images ... "
docker-compose stop
echo "[*] stopped docker-compose images"

echo "[ ] removing docker-compose images ... "
echo y | docker-compose rm
echo "[*] removed docker-compose images"

echo "[ ] starting docker-compose images ... "
docker-compose up