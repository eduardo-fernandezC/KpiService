# KpiService

Microservicio Spring Boot encargado de calcular y exponer KPIs de ventas a partir de un servicio de datos.

## Requisitos

- Java 21
- Maven
- Tener disponible la URL del servicio de datos en `DATASERVICE_URL`

## Instalación y ejecución

```bash
mvn clean install
mvn spring-boot:run
```

El servicio se ejecuta por defecto en `http://localhost:8092`.

## Configuración

Define la variable de entorno `DATASERVICE_URL` con la URL del microservicio que entrega ventas, productos y detalles de venta.

## Funcionalidades principales

- Cálculo de KPIs de ventas por día, mes y globales.
- Identificación de productos más y menos vendidos.
- Comparación de crecimiento mensual.
- Ranking de vendedores y rendimiento por sucursal.
