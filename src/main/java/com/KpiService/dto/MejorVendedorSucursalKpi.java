package com.KpiService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MejorVendedorSucursalKpi {

    private String sucursal; 
    private String vendedor; 
    private Double totalVendido;
}
