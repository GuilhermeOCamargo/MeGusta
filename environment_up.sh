#!/bin/sh

sudo docker-compose down

mkdir -p docker_environment/discovery/
mkdir -p docker_environment/proxy/

find . -name "pom.xml" -exec mvn package -f '{}' \;
cp zuul-server/target/zuul-service.jar docker_environment/proxy/
cp eureka-server/target/eureka-service.jar docker_environment/discovery/

sudo docker-compose up -d