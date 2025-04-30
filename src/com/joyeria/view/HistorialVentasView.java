package com.joyeria.view;

import com.joyeria.controller.VentaController;
import com.joyeria.model.VentaVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistorialVentasView extends JFrame {

    private JTable tablaVentas;
    private VentaController ventaController;

    public HistorialVentasView() {
        ventaController = new VentaController();
        inicializarComponentes();
        cargarVentasEnTabla();
    }

    private void inicializarComponentes() {
        setTitle("Historial de Ventas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tablaVentas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaVentas);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarVentasEnTabla() {
        List<Object[]> listaVentas = ventaController.obtenerHistorialVentasConNombre();

        String[] columnas = {"ID Venta", "ID Cliente", "Nombre de Joya", "Cantidad", "Fecha"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        for (Object[] venta : listaVentas) {
            modeloTabla.addRow(venta);
        }

        tablaVentas.setModel(modeloTabla);
    }
}
