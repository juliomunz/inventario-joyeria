package com.joyeria.model;

public class JoyaVO {
    private int id;
    private String nombre;
    private String material;
    private double peso;
    private double precio;
    private int stock;

    // Constructor vacío
    public JoyaVO() {}

    // Constructor completo
    public JoyaVO(int id, String nombre, String material, double peso, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.material = material;
        this.peso = peso;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return nombre + " (" + material + ")";
    }

}

