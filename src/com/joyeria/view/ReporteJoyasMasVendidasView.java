package com.joyeria.view;

import com.joyeria.controller.VentaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReporteJoyasMasVendidasView extends JFrame {

    private JTable tablaReporte;
    private VentaController ventaController;

    public ReporteJoyasMasVendidasView() {
        ventaController = new VentaController();
        inicializarComponentes();
        cargarReporte();
    }

    private void inicializarComponentes() {
        setTitle("Reporte - Joyas Más Vendidas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tablaReporte = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaReporte);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarReporte() {
        List<Object[]> datos = ventaController.obtenerJoyasMasVendidasConNombre();

        String[] columnas = {"Nombre de Joya", "Total Vendido"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }

        tablaReporte.setModel(modelo);
    }
}