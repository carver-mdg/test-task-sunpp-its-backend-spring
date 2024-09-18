# Тестовое задание для СИТ ЮУ АЭС.

Web application для управления доступом к сервисам системы.
Пользователь admin-sys добавляет работников и пользователей 
(из числа работников) в систему, создаёт сервисы и назначает 
им владельца и админа, добавляет цеха и штатные единицы.

Когда пользователь системы запрашивает доступ к сервису, 
владелец сервиса может дать доступ после этого админ сервиса
принимает окончательное решение
`(User requests access -> Owner -> Admin).`


## Установка:

Для сборки образа OracleDB для docker:
```shell
git clone https://github.com/oracle/docker-images.git
cd docker-images/OracleDatabase/SingleInstance/dockerfiles
./buildContainerImage.sh -v 21.3.0 -t oracle-db-xe:21.3.0 -x
```

После сборки образа создаём и поднимаем контейнер через docker-compose.
```shell
cd dev_utils/db
docker-compose up
```

Код для создания пользователя в OracleDB: 
```sql
CREATE USER c##sit IDENTIFIED BY password;
GRANT RESOURCE TO c##sit;
GRANT CONNECT TO c##sit;
GRANT CREATE VIEW TO c##sit;
GRANT CREATE SESSION TO c##sit;
GRANT UNLIMITED TABLESPACE TO c##sit;
```

## Запуск
В режиме develop:
```java -jar -Dspring.profiles.active=dev demo-app.jar```

В режиме production для телефона:
```java -jar -Dspring.profiles.active=prod-phone demo-app.jar```