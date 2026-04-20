package com.KpiService.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    private Long idVenta;
    private Double total;
    private LocalDate fecha;
    private Sucursal sucursal;
}
