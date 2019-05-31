#!/bin/sh

sudo docker-compose down

mkdir -p docker_environment/discovery/
mkdir -p docker_environment/proxy/
mkdir -p docker_environment/user_service/


mvn clean -Dmaven.test.skip=true package -f eureka-server/pom.xml
cp eureka-server/target/eureka-service.jar docker_environment/discovery/

mvn clean -Dmaven.test.skip=true package -f zuul-server/pom.xml
cp zuul-server/target/zuul-service.jar docker_environment/proxy/

mvn clean -Dmaven.test.skip=true package -f user-service/pom.xml
cp user-service/target/user-service.jar docker_environment/user_service/

sudo docker-compose up -d