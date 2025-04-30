package com.joyeria.model;

import java.time.LocalDate;

public class VentaVO {
    private int id;
    private int idCliente;
    private int idJoya;
    private int cantidad;
    private LocalDate fecha;

    public VentaVO() {}

    public VentaVO(int id, int idCliente, int idJoya, int cantidad, LocalDate fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.idJoya = idJoya;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdJoya() {
        return idJoya;
    }

    public void setIdJoya(int idJoya) {
        this.idJoya = idJoya;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
