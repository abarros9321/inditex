
# Inditex

Este es el test técnico para Inditex.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Referencia de la API](#referencia-de-la-api)
- [Base de Datos](#base-de-datos)
- [Pruebas](#pruebas)
- [Cobertura de Código](#cobertura-de-código)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Autor](#autor)

## Descripción
Este proyecto es una aplicación Spring Boot diseñada para manejar precios de productos para diferentes marcas y fechas específicas.

## Instalación
1. Clona el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd inditex
   ```
2. Instala las dependencias:
   ```bash
   ./mvnw install
   ```

## Ejecución
1. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
2. Abre tu navegador preferido y navega a `http://localhost:8080/swagger-ui.html` para acceder a Swagger UI.
3. Para acceder a la consola H2, navega a `http://localhost:8080/h2-console/` y utiliza las siguientes credenciales:
    - Usuario: `a`
    - Contraseña: `a`
    - JDBC URL: `jdbc:h2:mem:inditex`

## Referencia de la API
### Obtener el precio de un producto en una fecha y hora específicas

```http
GET /price/{productId}/{brandId}/{date}
```

| Parámetro  | Tipo   | Descripción                             |
|------------|--------|-----------------------------------------|
| `productId`| `Long` | **Requerido**. Identificador del producto|
| `brandId`  | `Long` | **Requerido**. Identificador de la marca|
| `date`     | `String` | **Requerido**. Fecha de aplicación       |

### Posibles Respuestas

#### 200 OK
La solicitud fue exitosa y devuelve el precio del producto.
```json
{
   "productId": 1,
   "brandId": 1,
   "price": 25.45,
   "currency": "EUR",
   "startDate": "2024-07-29T00:00:00",
   "endDate": "2024-07-29T23:59:59"
}
```
#### 404 NOT FOUND
El producto o la marca no existen, o no hay precio disponible para la fecha solicitada.
```json
{
   "timestamp": "2024-07-29T00:00:00",
   "status": 404,
   "error": "Not Found",
   "message": "Price not found",
   "path": "/price/1/1/2024-07-29T00:00:00"
}
```
#### 400 BAD REQUEST
La solicitud contiene parámetros inválidos o malformados.
```json
{
   "timestamp": "2024-07-29T00:00:00",
   "status": 400,
   "error": "Bad Request",
   "message": "Invalid date format",
   "path": "/price/1/1/invalid-date"
}
```

## Base de Datos
La aplicación utiliza una base de datos en memoria H2 para almacenar los datos. Puedes acceder a la consola H2 para ver y modificar los datos durante el tiempo de ejecución.

## Pruebas
Las pruebas están ubicadas en la carpeta `src/test/java`. Hay pruebas disponibles para los servicios, repositorios y controladores.

Para ejecutar las pruebas:
```bash
./mvnw test
```

Los casos de prueba específicos mencionados en el archivo `PriceControllerTest.class` cubren los cinco casos de prueba especificados en el enunciado del test.

## Cobertura de Código
Este proyecto utiliza JaCoCo para medir la cobertura de código. Después de ejecutar las pruebas, puedes generar el informe de cobertura con el siguiente comando:

```bash
./mvnw jacoco:report
```

El informe HTML se generará en `target/site/jacoco/index.html`. Puedes abrir este archivo en tu navegador preferido para ver los detalles de la cobertura de código.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 4.5.8
- Lombok
- JPA (Java Persistence API)
- JUnit
- Mockito
- H2 Database
- Swagger
- JaCoCo

## Autor
- **Alexander Barros**
