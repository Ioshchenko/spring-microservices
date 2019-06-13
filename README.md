# spring-microservices

**Catalog service** holds online store product data in-memory.

**Inventory service** holds online store product availability data.

**Product service** returns product data to end-clients.

## Zipkin server
`docker run -d -p 9411:9411 openzipkin/zipkin`

docker-compose up -d

## Start modules in docker:
`docker-compose up -d --build --force-recreate --no-cache registry-service `

`docker-compose up -d service-registry catalog-service inventory-service product-api`

docker-compose up -d --build --force-recreate inventory

docker logs service-registry


catalog-service:
    container_name: catalog-service
    build: ./catalog
    ports:
      - "19010:9010"
      - "19797:8787"
    entrypoint: /bin/sh
    command: >
      -c "java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar /app.jar"

  product-api:
    container_name: product-api
    build: ./productapi
    ports:
      - "19030:9030"
      - "39797:8787"
    entrypoint: /bin/sh
    command: >
      -c "java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar /app.jar"




version: '3'
services:
  registry-service:
    build: ./registry
    ports:
      - "8761:8761"
      - "18787:8787"
    entrypoint: /bin/sh
    command: >
      -c "java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar /app.jar"

  inventory-service:
    container_name: inventory-service
    build: ./inventory
    links:
      - registry-service
    ports:
      - "9020:9020"
      - "29797:8787"
    entrypoint: /bin/sh
    environment:
      - EUREKA_SERVER=registry-service
    command: >
      -c "java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar /app.jar"
