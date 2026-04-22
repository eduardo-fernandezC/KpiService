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

    @GetMapping("/ventas-totales")
    public KpiResponse GetVentasTotales() {
        return kpiService.ventasTotales();
    }

    @GetMapping("/ventas-cantidad")
    public KpiResponse GetCantidadVentas() {
        return kpiService.cantidadVentas();
    }

    @GetMapping("/ventas-promedio")
    public KpiResponse GetPromedioVentas() {
        return kpiService.promedioVentas();
    }

    @GetMapping("/ventas-hoy")
    public KpiResponse ventasHoy() {
        return kpiService.ventasHoy();
    }

    @GetMapping("/ventas-mes")
    public KpiResponse ventasMes() {
        return kpiService.ventasMes();
    }

    @GetMapping("/ventas-hoy-cantidad")
    public KpiResponse cantidadHoy() {
        return kpiService.cantidadVentasHoy();
    }

    @GetMapping("/ventas-mes-cantidad")
    public KpiResponse cantidadMes() {
        return kpiService.cantidadVentasMes();
    }

    @GetMapping("/ventas-hoy-promedio")
    public KpiResponse promedioHoy() {
        return kpiService.promedioVentasHoy();
    }

    @GetMapping("/ventas-mes-promedio")
    public KpiResponse promedioMes() {
        return kpiService.promedioVentasMes();
    }

    @GetMapping("/ventas-crecimiento")
    public KpiResponse crecimiento() {
        return kpiService.crecimientoVentas();
    }
}
