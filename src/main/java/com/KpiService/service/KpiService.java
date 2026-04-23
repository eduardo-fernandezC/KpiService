package com.KpiService.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KpiService.client.DataClient;
import com.KpiService.dto.KpiResponse;
import com.KpiService.dto.ProductoKpi;
import com.KpiService.model.DetalleVenta;
import com.KpiService.model.Venta;

@Service
public class KpiService {

    @Autowired
    private DataClient dataClient; // inyectamos el cliente para obtener los datos

    // ventas totales
    public KpiResponse ventasTotales() {
        List<Venta> ventas = dataClient.getVentas(); // obtenemos las ventas desde el microservicio de datos

        double totalVentas = ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        return new KpiResponse("Ventas Totales", totalVentas);
    }

    // cantidad de ventas 
    public KpiResponse cantidadVentas() {
        List<Venta> ventas = dataClient.getVentas();

        double cantVentas = ventas.size();

        return new KpiResponse("Cantidad Ventas", cantVentas);
    }

    // promedio ventas
    public KpiResponse promedioVentas() {
        List<Venta> ventas = dataClient.getVentas();

        double promedio = ventas.stream()
            .mapToDouble(Venta::getTotal)
            .average() // sacar promedios
            .orElse(0);
        
        double redondeado = Math.round(promedio * 10.0) / 10.0;

        return new KpiResponse("Promedio Ventas", redondeado);
    }

    // ventas hoy
    public KpiResponse ventasHoy() {
        LocalDate hoy = LocalDate.now();

        double total = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null && v.getFecha().isEqual(hoy))
                .mapToDouble(Venta::getTotal)
                .sum();

        return new KpiResponse("Ventas hoy", total);
    }

    //ventas mes
    public KpiResponse ventasMes() {
        YearMonth mesActual = YearMonth.now();  

        double total = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(mesActual))
                .mapToDouble(Venta::getTotal)
                .sum();

        return new KpiResponse("Ventas mes", total);
    }

    //cantidad hoy
    public KpiResponse cantidadVentasHoy() {
        LocalDate hoy = LocalDate.now();

        double cantidad = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null && v.getFecha().isEqual(hoy))
                .count();

        return new KpiResponse("Cantidad ventas hoy", cantidad);
    }

    //cantidad mes
    public KpiResponse cantidadVentasMes() {
        YearMonth mesActual = YearMonth.now();

        double cantidad = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(mesActual))
                .count();

        return new KpiResponse("Cantidad ventas mes", cantidad);
    }

    // promedio hoy
    public KpiResponse promedioVentasHoy() {
        LocalDate hoy = LocalDate.now();

        double promedio = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null && v.getFecha().isEqual(hoy))
                .mapToDouble(Venta::getTotal)
                .average()
                .orElse(0);
        
        double redondeado = Math.round(promedio * 10.0) / 10.0;

        return new KpiResponse("Promedio ventas hoy", redondeado);
    }

    // promedio mes
    public KpiResponse promedioVentasMes() {
        YearMonth mesActual = YearMonth.now();

        double promedio = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(mesActual))
                .mapToDouble(Venta::getTotal)
                .average()
                .orElse(0);

        double redondeado = Math.round(promedio * 10.0) / 10.0;

        return new KpiResponse("Promedio ventas mes", redondeado);
    }

    // crecimiento mensual
    public KpiResponse crecimientoVentas() {
        YearMonth actual = YearMonth.now();
        YearMonth anterior = actual.minusMonths(1);

        double actualTotal = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(actual))
                .mapToDouble(Venta::getTotal)
                .sum();

        double anteriorTotal = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(anterior))
                .mapToDouble(Venta::getTotal)
                .sum();

        double crecimiento = anteriorTotal == 0 ? 0 :
        ((actualTotal - anteriorTotal) / anteriorTotal) * 100;

        double redondeado = Math.round(crecimiento * 10.0) / 10.0;

        return new KpiResponse("Crecimiento ventas", redondeado);
    }

    // producto mas vendido
    public ProductoKpi productoMasVendido() {

        List<DetalleVenta> detalles = dataClient.getDetalleVentas();

        return detalles.stream()
                .collect(Collectors.groupingBy( // Collectors.groupingBy para agrupar por producto
                d -> d.getProducto().getNombre(),
                Collectors.summingInt(DetalleVenta::getCantidad)
                ))
                .entrySet().stream() // entrySet es para convertir el mapa en un stream de entradas (clave-valor)
                .max(Map.Entry.comparingByValue()) // max para obtener el producto con la mayor cantidad vendida comparando por el valor (cantidad)
                .map(e -> new ProductoKpi(e.getKey(), e.getValue()))
                .orElse(null);
    }

    // producto menos vendido
    public ProductoKpi productoMenosVendido() {

    List<DetalleVenta> detalles = dataClient.getDetalleVentas();

    return detalles.stream()
        .collect(Collectors.groupingBy(
            d -> d.getProducto().getNombre(),
            Collectors.summingInt(DetalleVenta::getCantidad)
        ))
        .entrySet().stream()
        .min(Map.Entry.comparingByValue()) // min para obtener el producto con la menor cantidad vendida comparando por el valor (cantidad)
        .map(e -> new ProductoKpi(e.getKey(), e.getValue()))
        .orElse(null);
    }
}