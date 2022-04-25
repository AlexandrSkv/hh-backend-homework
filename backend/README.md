# CHECKLY

Школьный проект, Сервис проверки рекомендаций

В текущем проекте уже есть сконфигурированное nab-приложение.

До старта необходимо поднять PostgreSQL в докере. Старт контейнера:

`docker-compose up`

## Старт приложения в докере:
Собрать образ:
`mvn clean install -Pdocker`

Запустить образ:
`docker run -v "$(pwd)"/src/etc:/app/etc:ro -v "$(pwd)":/app/logs --network=host checkly-backend`

Логи сохраняются в файл logs.txt в директории приложения

## Старт приложения на локальной машине, maven версии 3.6.3:

`mvn install exec:java`



