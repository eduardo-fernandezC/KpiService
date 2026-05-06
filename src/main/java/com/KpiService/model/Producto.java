package com.KpiService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long idProducto;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
}
