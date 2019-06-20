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

docker-compose up -d --build --force-recreate registry-service
docker-compose up -d --build --force-recreate inventory-service
docker-compose up -d --build --force-recreate catalog-service

docker logs service-registry

docker exec registry-service cat /etc/hosts
docker exec inventory-service cat /etc/hosts
docker exec inventory-service ping registry-service
docker exec registry-service ping inventory-service
