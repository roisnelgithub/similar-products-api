# Similar Products API – Documentación

Este proyecto es una aplicación desarrollada con **Spring Boot**, siguiendo los principios de **Arquitectura Limpia (Clean Architecture)**.

La finalidad de esta API es obtener productos similares a un producto dado por su ID, consumiendo servicios externos a través de HTTP.  
Estos servicios son simulados mediante **mocks proporcionados por AMS Solutions**.

> Los mocks no se incluyen directamente en este repo.

---

## Tecnologías utilizadas

- Java 17  
- Spring Boot 3.4.x  
- Docker / Docker Compose

---

## Configurar variables de entorno
La aplicación puede usar la variable de entorno PRODUCT_SERVICE_URL para definir la URL del servicio de productos.

Por defecto:
```bash
http://localhost:3001
```
---

## Levantar mocks

Debes obtener los mocks y levantarlos usando Docker:

```bash
docker compose up -d
```

---

## Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```


---

## Probar endpoints


### Verificación rápida

Prueba rápida del endpoint principal:

```bash
curl http://localhost:5000/product/1/similar
```

> Devuelve un array JSON con productos similares.

### Acceder a Swagger

```text
http://localhost:5000/swagger-ui.html
```