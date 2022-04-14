FROM    adoptopenjdk/maven-openjdk11:latest

RUN     mkdir /app

WORKDIR /app

COPY    /backend/pom.xml .
COPY    /backend/src ./src

RUN  mvn clean install -DskipTests

CMD "mvn" "exec:java"
