# CHECKLY
Школьный проект, Сервис проверки рекомендаций

## Как поднять приложение с помощью Docker Compose 
* Сборка образа сервиса frontend

В папке frontend запускаем команду:

    docker build -t nginx-checkly .
* Сборка образа сервиса backend

В папке backend запускаем команду:

    mvn clean install -Pdocker

* Быстрое поднятие всех контейнеров

В папке CHECKLY запускаем команду:

    docker-compose up

* Открытие клиентского приложения в браузере [http://localhost/](http://localhost/)
