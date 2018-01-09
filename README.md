# TODO-NISUM

This sample project that shows how to connect from Java App to MongoDB.

### Prerequisites

To run locally:
* JAVA 1.8

## Running the tests

from "todo-nisum" has working directory execute:

```
./gradlew test
```

### How to launch API

from "todo-nisum" has working directory execute:

```
./gradlew bootRun
```

### How to consume API throw
This API is exposed through http protocol.


1. To Load All Todo items:
```
curl -X GET \
  http://localhost:8080/todos \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 332b6274-8f40-34f1-f457-0a391b09f2bf'
```

2. To create a new Todo item:
```
curl -X POST \
  http://localhost:8080/todos \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 28429a79-d8e7-c491-e762-25de3f92bc16' \
  -d '{
    "title": "todo-nisum",
    "done": false
}'
```

3. To load a Todo item providing its id:
```
curl -X GET \
  http://localhost:8080/todos/5a551f5b7a9be54a06fcf820 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 46125cab-b883-42aa-9904-af930add4b25'
```

4. To update a Todo item
```
curl -X PUT \
  http://localhost:8080/todos \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 29234fff-bdc8-7afe-b81f-655c4770e542' \
  -d '{
    "id": "5a551f5b7a9be54a06fcf820",
    "title": "toooooooooooooooooooodo-nisum",
    "done": true
}'
```

5. To delete a Todo item providing its id:
```
curl -X DELETE \
  http://localhost:8080/todos/5a552be07a9be556f3e74a20 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 13a36c5d-43e6-efd6-4a49-41052d865042'
```