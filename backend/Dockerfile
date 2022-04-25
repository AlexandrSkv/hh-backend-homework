FROM openjdk:18.0.1-slim-buster
COPY target/checkly-backend.jar /app/
RUN mkdir /app/logs
ENTRYPOINT ["bin/sh", "-c", "java -jar -DsettingsDir=/app/etc /app/checkly-backend.jar > app/logs/dockerlogs.txt 2>&1"]
