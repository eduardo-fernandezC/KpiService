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

## Endpoints

Base URL: `/api/v1/kpis`

- `GET /ventas-totales` - Total de ventas.
- `GET /ventas-cantidad` - Cantidad total de ventas.
- `GET /ventas-promedio` - Promedio general de ventas.
- `GET /ventas-hoy` - Total vendido hoy.
- `GET /ventas-mes` - Total vendido en el mes actual.
- `GET /ventas-hoy-cantidad` - Cantidad de ventas de hoy.
- `GET /ventas-mes-cantidad` - Cantidad de ventas del mes actual.
- `GET /ventas-hoy-promedio` - Promedio de ventas de hoy.
- `GET /ventas-mes-promedio` - Promedio de ventas del mes actual.
- `GET /ventas-crecimiento` - Crecimiento porcentual respecto al mes anterior.
- `GET /producto-mas-vendido` - Producto con mayores ventas.
- `GET /producto-menos-vendido` - Producto con menores ventas.
- `GET /mejor-vendedor-sucursal` - Mejor vendedor por sucursal.
- `GET /sucursal-rendimiento` - Rendimiento de sucursales.

## Funcionalidades principales

- Cálculo de KPIs de ventas por día, mes y globales.
- Identificación de productos más y menos vendidos.
- Comparación de crecimiento mensual.
- Ranking de vendedores y rendimiento por sucursal.
