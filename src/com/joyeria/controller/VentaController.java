package com.joyeria.controller;

import com.joyeria.dao.VentaDAO;
import com.joyeria.model.VentaVO;

import java.util.List;

public class VentaController {

    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public boolean registrarVenta(VentaVO venta) {
        return ventaDAO.registrarVenta(venta);
    }

    public List<Object[]> obtenerHistorialVentasConNombre() {
        return ventaDAO.listarVentasConNombreJoya();
    }
    public List<Object[]> obtenerJoyasMasVendidasConNombre() {
        return ventaDAO.obtenerJoyasMasVendidasConNombre();
    }
}