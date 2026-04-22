package com.KpiService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KpiService.dto.KpiResponse;
import com.KpiService.service.KpiService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/kpis")
public class KpiController {

    @Autowired
    private KpiService kpiService;

    @GetMapping("/ventas-totales") // endpoint para obtener las ventas totales
    public KpiResponse GetVentasTotales() {
        return kpiService.ventasTotales();
    }

    @GetMapping("/ventas-cantidad") // endpoint para obtener la cantidad de ventas
    public KpiResponse GetCantidadVentas() {
        return kpiService.cantidadVentas();
    }

    @GetMapping("/ventas-promedio") // endpoint para obtener el promedio de ventas
    public KpiResponse GetPromedioVentas() {
        return kpiService.promedioVentas();
    }

    @GetMapping("/ventas-hoy") // endpoint para obtener las ventas del dia de hoy
    public KpiResponse ventasHoy() {
        return kpiService.ventasHoy();
    }

    @GetMapping("/ventas-mes") // endpoint para obtener las ventas del mes actual
    public KpiResponse ventasMes() {
        return kpiService.ventasMes();
    }

    @GetMapping("/ventas-hoy-cantidad") // endpoint para obtener la cantidad de ventas del dia de hoy
    public KpiResponse cantidadHoy() {
        return kpiService.cantidadVentasHoy();
    }

    @GetMapping("/ventas-mes-cantidad") // endpoint para obtener la cantidad de ventas del mes actual
    public KpiResponse cantidadMes() {
        return kpiService.cantidadVentasMes();
    }

    @GetMapping("/ventas-hoy-promedio") // endpoint para obtener el promedio de ventas del dia de hoy
    public KpiResponse promedioHoy() {
        return kpiService.promedioVentasHoy();
    }

    @GetMapping("/ventas-mes-promedio") // endpoint para obtener el promedio de ventas del mes actual
    public KpiResponse promedioMes() {
        return kpiService.promedioVentasMes();
    }

    @GetMapping("/ventas-crecimiento")
    public KpiResponse crecimiento() {
        return kpiService.crecimientoVentas();
    }
}
