#### TaskExecutor #
Приложение читает задачи из топика Kafka и выполняет их, сохраняя логи в базу данных.

Система обеспечивает:

- Чтение задач из топика Kafka
- Асинхронное выполнение задач пулом воркеров
- Сохранение информации о задачах в базе данных
- Доступ по REST API к итоговым и промежуточным результатам выполнения задач
- Управление миграциями БД с Liquibase

#### Стек: #
Spring Boot, Hibernate, PostgreSQL, Docker, Kafka, Liquibase

#### Настройки: #

Количество потоков, выполняющих задачи, а также топик, откуда они считываются, можно указать в файле application.properties.

Параметры подключения к базе данных (url, username, password) и Kafka (bootstrap-servers) задаются в docker-compose.yaml.

#### Запуск: #

```
mvn clean install
docker-compose --project-name executor up --build -d
```

#### Описание API: #
http://localhost:8080/swagger-ui/index.html