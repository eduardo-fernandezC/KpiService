package com.KpiService.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {
    private Long idVenta;
    private Double total;
    private LocalDate fecha;
    private String sucursalNombre;
    private Long idEmpleado;
    private String empleadoNombre;
}
