#!/bin/sh

sudo docker-compose down

mkdir -p docker_environment/discovery/
mkdir -p docker_environment/proxy/

mvn clean package -f eureka-server/pom.xml
cp eureka-server/target/eureka-service.jar docker_environment/discovery/

mvn clean package -f zuul-server/pom.xml
cp zuul-server/target/zuul-service.jar docker_environment/proxy/

sudo docker-compose up -d