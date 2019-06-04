# spring-microservices

**Catalog service** holds online store product data in-memory.

**Inventory service** holds online store product availability data.

**Product service** returns product data to end-clients.

## Zipkin server
`docker run -d -p 9411:9411 openzipkin/zipkin`