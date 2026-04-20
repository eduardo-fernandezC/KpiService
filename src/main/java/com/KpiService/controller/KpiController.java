package com.KpiService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KpiService.dto.KpiResponse;
import com.KpiService.service.KpiService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/kpis")
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
}
