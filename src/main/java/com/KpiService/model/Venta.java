package com.KpiService.model;

import java.time.LocalDate;

public class Venta {

    private Long id;
    private Double total;
    private LocalDate fecha;
    private Sucursal sucursal;

    public Venta() {
    }

    public Venta(Long id, Double total, LocalDate fecha, Sucursal sucursal) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
