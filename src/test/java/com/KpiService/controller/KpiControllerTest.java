package com.KpiService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.KpiService.dto.KpiResponse;
import com.KpiService.dto.MejorVendedorSucursalKpi;
import com.KpiService.dto.ProductoKpi;
import com.KpiService.dto.SucursalKpi;
import com.KpiService.service.KpiService;

@ExtendWith(MockitoExtension.class)
class KpiControllerTest {

    @Mock
    private KpiService kpiService;

    @InjectMocks
    private KpiController kpiController;

    @Test
    void GetVentasTotales_retornaVentasTotales() {

        KpiResponse mockResponse =
                new KpiResponse("Ventas Totales", 1000.0);

        when(kpiService.ventasTotales())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.GetVentasTotales();

        assertEquals("Ventas Totales",
                response.getNombre());

        assertEquals(1000.0,
                response.getValor());

        verify(kpiService).ventasTotales();
    }

    @Test
    void GetCantidadVentas_retornaCantidadVentas() {

        KpiResponse mockResponse =
                new KpiResponse("Cantidad Ventas", 25.0);

        when(kpiService.cantidadVentas())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.GetCantidadVentas();

        assertEquals(25.0,
                response.getValor());

        verify(kpiService).cantidadVentas();
    }

    @Test
    void GetPromedioVentas_retornaPromedio() {

        KpiResponse mockResponse =
                new KpiResponse("Promedio Ventas", 500.5);

        when(kpiService.promedioVentas())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.GetPromedioVentas();

        assertEquals(500.5,
                response.getValor());

        verify(kpiService).promedioVentas();
    }

    @Test
    void ventasHoy_retornaVentasHoy() {

        KpiResponse mockResponse =
                new KpiResponse("Ventas hoy", 350.0);

        when(kpiService.ventasHoy())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.ventasHoy();

        assertEquals(350.0,
                response.getValor());

        verify(kpiService).ventasHoy();
    }

    @Test
    void ventasMes_retornaVentasMes() {

        KpiResponse mockResponse =
                new KpiResponse("Ventas mes", 4000.0);

        when(kpiService.ventasMes())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.ventasMes();

        assertEquals(4000.0,
                response.getValor());

        verify(kpiService).ventasMes();
    }

    @Test
    void cantidadHoy_retornaCantidadHoy() {

        KpiResponse mockResponse =
                new KpiResponse("Cantidad ventas hoy", 5.0);

        when(kpiService.cantidadVentasHoy())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.cantidadHoy();

        assertEquals(5.0,
                response.getValor());

        verify(kpiService).cantidadVentasHoy();
    }

    @Test
    void cantidadMes_retornaCantidadMes() {

        KpiResponse mockResponse =
                new KpiResponse("Cantidad ventas mes", 50.0);

        when(kpiService.cantidadVentasMes())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.cantidadMes();

        assertEquals(50.0,
                response.getValor());

        verify(kpiService).cantidadVentasMes();
    }

    @Test
    void promedioHoy_retornaPromedioHoy() {

        KpiResponse mockResponse =
                new KpiResponse("Promedio ventas hoy", 250.5);

        when(kpiService.promedioVentasHoy())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.promedioHoy();

        assertEquals(250.5,
                response.getValor());

        verify(kpiService).promedioVentasHoy();
    }

    @Test
    void promedioMes_retornaPromedioMes() {

        KpiResponse mockResponse =
                new KpiResponse("Promedio ventas mes", 700.0);

        when(kpiService.promedioVentasMes())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.promedioMes();

        assertEquals(700.0,
                response.getValor());

        verify(kpiService).promedioVentasMes();
    }

    @Test
    void crecimiento_retornaCrecimiento() {

        KpiResponse mockResponse =
                new KpiResponse("Crecimiento ventas", 12.5);

        when(kpiService.crecimientoVentas())
                .thenReturn(mockResponse);

        KpiResponse response =
                kpiController.crecimiento();

        assertEquals(12.5,
                response.getValor());

        verify(kpiService).crecimientoVentas();
    }

    @Test
    void productoMasVendido_retornaProducto() {

        ProductoKpi producto =
                new ProductoKpi("Notebook", 100);

        when(kpiService.productoMasVendido())
                .thenReturn(producto);

        ProductoKpi response =
                kpiController.productoMasVendido();

        assertEquals("Notebook",
                response.getNombreProducto());

        verify(kpiService).productoMasVendido();
    }

    @Test
    void productoMenosVendido_retornaProducto() {

        ProductoKpi producto =
                new ProductoKpi("Mouse", 2);

        when(kpiService.productoMenosVendido())
                .thenReturn(producto);

        ProductoKpi response =
                kpiController.productoMenosVendido();

        assertEquals("Mouse",
                response.getNombreProducto());

        verify(kpiService).productoMenosVendido();
    }

    @Test
    void mejorVendedorPorSucursal_retornaLista() {

        MejorVendedorSucursalKpi vendedor =
                new MejorVendedorSucursalKpi(
                        "Centro",
                        "Juan",
                        5000.0);

        when(kpiService.mejorVendedorPorSucursal())
                .thenReturn(List.of(vendedor));

        List<MejorVendedorSucursalKpi> response =
                kpiController.mejorVendedorPorSucursal();

        assertEquals(1,
                response.size());

        assertEquals("Juan",
                response.get(0).getVendedor());

        verify(kpiService)
                .mejorVendedorPorSucursal();
    }

    @Test
    void rendimientoSucursales_retornaMapa() {

        Map<String,SucursalKpi> mock =
                Map.of(
                        "mayor",
                        new SucursalKpi("Centro",10000.0)
                );

        when(kpiService.rendimientoSucursales())
                .thenReturn(mock);

        Map<String,SucursalKpi> response =
                kpiController.rendimientoSucursales();

        assertEquals(
                "Centro",
                response.get("mayor")
                        .getSucursal()
        );

        verify(kpiService)
                .rendimientoSucursales();
    }

}