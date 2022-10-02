
# Requerimientos para la Prueba


En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

|BRAND_ID|START_DATE|END_DATE|PRICE_LIST|PRODUCT_ID|PRIORITY|PRICE|CURR|
| --- | --- | --- | --- | --- | --- | --- | --- |
|1|2020-06-14-00.00.00|2020-12-31-23.59.59|1|35455|0|35.50|EUR|
|1|2020-06-14-15.00.00|2020-06-14-18.30.00|2|35455|1|25.45|EUR|
|1|2020-06-15-00.00.00|2020-06-15-11.00.00|3|35455|1|30.50|EUR|
|1|2020-06-15-16.00.00|2020-12-31-23.59.59|4|35455|1|38.95|EUR|

### Campos

- BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
- START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
- PRICE_LIST: Identificador de la tarifa de precios aplicable.
- PRODUCT_ID: Identificador código de producto.
- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
- PRICE: precio final de venta.
- CURR: iso de la moneda.

### Se Pide

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
- Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
- Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

### Precondiciones

- Se debe utilizar una base de datos en memoria (tipo h2).
- Se debe inicializar con los datos del ejemplo.
- Se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere.
- Se puede elegir el tipo de dato que se considere adecuado para los mismos.

### Resultados de la Prueba
Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

- **Test 1:** petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA).
- **Test 2:** petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA).
- **Test 3:** petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA).
- **Test 4:** petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA).
- **Test 5:** petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA).

### Se valorará:

- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.

# Desarrollo de la Solución


## API Price

#### Obtener precio por fecha de aplicación, identificador de producto, identificador de cadena

```http
  GET /api/price/search
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `productId` | `Long` | **Required**. identificador de producto |
| `brandId` | `Long` | **Required**. identificador de cadena |
| `applicationDate` | `DateTime` | **Required**. fecha de aplicación |

### Autenticación basica
Se implementó autenticación basica con Spring Security, para lo cual se debe incluir en la petición al microservicio, la autenticación tipo Basic Auth:
- Usuario: admin
- Contraseña: admin.

### Resultados de la Prueba

#### Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)

```http
  GET http://localhost:8090/api/price/search?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
```

#### Respuesta Formato Json

```json
{
    "id": 1,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "status": true,
        "changeDate": "2022-09-26T20:30:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "startDate": "2020-06-14T05:00:00.000+00:00",
    "endDate": "2021-01-01T04:59:59.000+00:00",
    "priceList": 1,
    "product": {
        "id": 35455,
        "code": "35455/068",
        "name": "CAMISA SATINADA",
        "status": true,
        "changeDate": "2022-09-24T16:20:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "priority": 0,
    "price": 35.5,
    "codeIso": "EUR"
}
```

#### Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA).

```http
  GET http://localhost:8090/api/price/search?productId=35455&brandId=1&applicationDate=2020-06-14T16:00:00
```

#### Respuesta Formato Json

```json
{
    "id": 2,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "status": true,
        "changeDate": "2022-09-26T20:30:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "startDate": "2020-06-14T20:00:00.000+00:00",
    "endDate": "2020-06-14T23:30:00.000+00:00",
    "priceList": 2,
    "product": {
        "id": 35455,
        "code": "35455/068",
        "name": "CAMISA SATINADA",
        "status": true,
        "changeDate": "2022-09-24T16:20:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "priority": 1,
    "price": 25.45,
    "codeIso": "EUR"
}
```

#### Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA).

```http
  GET http://localhost:8090/api/price/search?productId=35455&brandId=1&applicationDate=2020-06-14T21:00:00
```

#### Respuesta Formato Json

```json
{
    "id": 1,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "status": true,
        "changeDate": "2022-09-26T20:30:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "startDate": "2020-06-14T05:00:00.000+00:00",
    "endDate": "2021-01-01T04:59:59.000+00:00",
    "priceList": 1,
    "product": {
        "id": 35455,
        "code": "35455/068",
        "name": "CAMISA SATINADA",
        "status": true,
        "changeDate": "2022-09-24T16:20:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "priority": 0,
    "price": 35.5,
    "codeIso": "EUR"
}
```

#### Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA).

```http
  GET http://localhost:8090/api/price/search?productId=35455&brandId=1&applicationDate=2020-06-15T10:00:00
```

#### Respuesta Formato Json

```json
{
    "id": 3,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "status": true,
        "changeDate": "2022-09-26T20:30:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "startDate": "2020-06-15T05:00:00.000+00:00",
    "endDate": "2020-06-15T16:00:00.000+00:00",
    "priceList": 3,
    "product": {
        "id": 35455,
        "code": "35455/068",
        "name": "CAMISA SATINADA",
        "status": true,
        "changeDate": "2022-09-24T16:20:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "priority": 1,
    "price": 30.5,
    "codeIso": "EUR"
}
```

#### Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA).

```http
  GET http://localhost:8090/api/price/search?productId=35455&brandId=1&applicationDate=2020-06-16T21:00:00
```

#### Respuesta Formato Json

```json
{
    "id": 4,
    "brand": {
        "id": 1,
        "name": "ZARA",
        "status": true,
        "changeDate": "2022-09-26T20:30:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "startDate": "2020-06-15T21:00:00.000+00:00",
    "endDate": "2021-01-01T04:59:59.000+00:00",
    "priceList": 4,
    "product": {
        "id": 35455,
        "code": "35455/068",
        "name": "CAMISA SATINADA",
        "status": true,
        "changeDate": "2022-09-24T16:20:00.000+00:00",
        "userUpdate": "ADMIN"
    },
    "priority": 1,
    "price": 38.95,
    "codeIso": "EUR"
}
```
## Authors

- [Pedro Antonio Chacón Garnica](https://www.linkedin.com/in/pedro-antonio-chac%C3%B3n-garnica-b9674b72/)

