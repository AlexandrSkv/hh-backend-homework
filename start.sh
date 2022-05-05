#!/bin/bash
cd ./backend
mvn clean install -Pdocker
cd ../
docker-compose build
docker-compose up