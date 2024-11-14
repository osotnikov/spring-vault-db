#!/bin/bash

docker-compose down -v --remove-orphans

if test "$1" = "--build"; then
    cd ..
    mvn clean install -DskipTests
    cd local_setup
fi

docker build .. -t myapp -f ./dev.Dockerfile

docker-compose up -d --wait