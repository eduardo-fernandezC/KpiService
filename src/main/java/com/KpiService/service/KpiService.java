package com.KpiService.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KpiService.client.DataClient;
import com.KpiService.dto.KpiResponse;
import com.KpiService.dto.MejorVendedorSucursalKpi;
import com.KpiService.dto.ProductoKpi;
import com.KpiService.dto.SucursalKpi;
import com.KpiService.dto.VentaResponse;
import com.KpiService.model.DetalleVenta;
import com.KpiService.model.Producto;

@Service
public class KpiService {

    @Autowired
    private DataClient dataClient; // inyectamos el cliente para obtener los datos

    // ventas totales
    public KpiResponse ventasTotales() {
        List<VentaResponse> ventas = dataClient.getVentas(); // obtenemos las ventas desde el microservicio de datos

        double totalVentas = ventas.stream()
            .map(VentaResponse::getTotal)
            .filter(total -> total != null)
            .mapToDouble(Double::doubleValue)
                .sum();

        return new KpiResponse("Ventas Totales", totalVentas);
    }

    // cantidad de ventas 
    public KpiResponse cantidadVentas() {
        List<VentaResponse> ventas = dataClient.getVentas();

        double cantVentas = ventas.size();

        return new KpiResponse("Cantidad Ventas", cantVentas);
    }

    // promedio ventas
    public KpiResponse promedioVentas() {
        List<VentaResponse> ventas = dataClient.getVentas();

        double promedio = ventas.stream()
            .map(VentaResponse::getTotal)
            .filter(total -> total != null)
            .mapToDouble(Double::doubleValue)
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
                .map(VentaResponse::getTotal)
                .filter(valor -> valor != null)
                .mapToDouble(Double::doubleValue)
                .sum();

        return new KpiResponse("Ventas hoy", total);
    }

    //ventas mes
    public KpiResponse ventasMes() {
        YearMonth mesActual = YearMonth.now();  

        double total = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(mesActual))
            .map(VentaResponse::getTotal)
            .filter(valor -> valor != null)
            .mapToDouble(Double::doubleValue)
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
                .map(VentaResponse::getTotal)
                .filter(valor -> valor != null)
                .mapToDouble(Double::doubleValue)
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
            .map(VentaResponse::getTotal)
            .filter(valor -> valor != null)
            .mapToDouble(Double::doubleValue)
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
            .map(VentaResponse::getTotal)
            .filter(valor -> valor != null)
            .mapToDouble(Double::doubleValue)
                .sum();

        double anteriorTotal = dataClient.getVentas().stream()
                .filter(v -> v.getFecha() != null &&
                        YearMonth.from(v.getFecha()).equals(anterior))
            .map(VentaResponse::getTotal)
            .filter(valor -> valor != null)
            .mapToDouble(Double::doubleValue)
                .sum();

        double crecimiento = anteriorTotal == 0 ? 0 :
        ((actualTotal - anteriorTotal) / anteriorTotal) * 100;

        double redondeado = Math.round(crecimiento * 10.0) / 10.0;

        return new KpiResponse("Crecimiento ventas", redondeado);
    }

    // producto mas vendido
    public ProductoKpi productoMasVendido() {

        return obtenerCantidadesPorProducto().entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(e -> new ProductoKpi(e.getKey(), e.getValue()))
            .orElse(null);
    }

    // producto menos vendido
    public ProductoKpi productoMenosVendido() {

        return obtenerCantidadesPorProducto().entrySet().stream()
            .min(Map.Entry.comparingByValue())
            .map(e -> new ProductoKpi(e.getKey(), e.getValue()))
            .orElse(null);
    }

        private Map<String, Integer> obtenerCantidadesPorProducto() {
        List<DetalleVenta> detalles = dataClient.getDetalleVentas();

        Map<String, Integer> cantidadesConProducto = detalles.stream()
            .filter(d -> d != null
                && d.getProducto() != null
                && d.getProducto().getNombre() != null
                && d.getCantidad() != null)
            .collect(Collectors.groupingBy(
                d -> d.getProducto().getNombre(),
                Collectors.summingInt(DetalleVenta::getCantidad)
            ));

        if (!cantidadesConProducto.isEmpty()) {
            return cantidadesConProducto;
        }

        Map<Long, String> nombrePorPrecio = dataClient.getProductos().stream()
            .filter(p -> p != null && p.getNombre() != null && p.getPrecio() != null)
            .collect(Collectors.toMap(
                p -> toCents(p.getPrecio()),
                Producto::getNombre,
                (existente, nuevo) -> existente,
                LinkedHashMap::new
            ));

        return detalles.stream()
            .filter(d -> d != null
                && d.getCantidad() != null
                && d.getCantidad() > 0
                && d.getSubtotal() != null)
            .collect(Collectors.groupingBy(
                d -> {
                    long precioUnitario = toCents(d.getSubtotal() / d.getCantidad());
                    return nombrePorPrecio.getOrDefault(precioUnitario, "Producto sin nombre");
                },
                Collectors.summingInt(DetalleVenta::getCantidad)
            ));
        }

        private long toCents(Double valor) {
        return Math.round(valor * 100.0);
        }

    // mejor vendedor por sucursal
    public List<MejorVendedorSucursalKpi> mejorVendedorPorSucursal() {

    List<VentaResponse> ventas = dataClient.getVentas();

    Map<String, Map<String, Double>> totalPorSucursalYVendedor = new HashMap<>();

    for (VentaResponse venta : ventas) {

        if (venta == null 
            || venta.getSucursalNombre() == null // || es para validar que no haya datos nulos en la venta, 
            // sucursal o vendedor, si hay datos nulos se omite esa venta
            || venta.getEmpleadoNombre() == null
            || venta.getTotal() == null) {
            continue;
        }

        String sucursal = venta.getSucursalNombre();
        String vendedor = venta.getEmpleadoNombre(); 

        totalPorSucursalYVendedor
            .computeIfAbsent(sucursal, k -> new HashMap<>())
            .merge(vendedor, venta.getTotal(), Double::sum);
    }

    return totalPorSucursalYVendedor.entrySet().stream()
        .map(entrySucursal -> {
            String sucursal = entrySucursal.getKey();

            return entrySucursal.getValue().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entryVendedor ->
                    new MejorVendedorSucursalKpi(
                        sucursal,
                        entryVendedor.getKey(),
                        entryVendedor.getValue()
                    )
                ).orElse(null);
        })
        .filter(kpi -> kpi != null)
        .collect(Collectors.toList());
    }

    // rendimiento de sucursales
    public Map<String, SucursalKpi> rendimientoSucursales() {

    List<VentaResponse> ventas = dataClient.getVentas();

    // Agrupar por sucursal y sumar ventas
    Map<String, Double> totalPorSucursal = ventas.stream()
        .filter(v -> v.getSucursalNombre() != null && v.getTotal() != null)
        .collect(Collectors.groupingBy(
            VentaResponse::getSucursalNombre,
            Collectors.summingDouble(VentaResponse::getTotal)
        ));

    // Buscar mayor y menor
    Map.Entry<String, Double> max = totalPorSucursal.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .orElse(null);

    Map.Entry<String, Double> min = totalPorSucursal.entrySet().stream()
        .min(Map.Entry.comparingByValue())
        .orElse(null);

    // Armar respuesta
    Map<String, SucursalKpi> resultado = new HashMap<>();

    if (max != null) {
        resultado.put("mayor", new SucursalKpi(max.getKey(), max.getValue()));
    }

    if (min != null) {
        resultado.put("menor", new SucursalKpi(min.getKey(), min.getValue()));
    }

    return resultado;
    }
}