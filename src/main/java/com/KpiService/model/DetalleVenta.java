package com.KpiService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    
    private Long idDetalle;
    private Integer cantidad;
    private Double subtotal;
    private Producto producto;
}
