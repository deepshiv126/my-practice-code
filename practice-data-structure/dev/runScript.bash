#!/bin/bash

## Pull SonarQube docker image.
docker pull sonarqube:latest

## Run docker SonarQube docker container.
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest


## build - but this command changes every time you need to generate token - because container is stateless in this case.
mvn sonar:sonar \
  -Dsonar.projectKey=my-practice-code \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=52f7ec2370fd61b1e800b28a90d542424cc4b7ed