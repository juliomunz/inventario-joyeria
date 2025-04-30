package com.joyeria.Main;
import com.joyeria.view.HistorialVentasView;
import com.joyeria.view.VentaView;
import com.joyeria.view.ReporteJoyasMasVendidasView;

import javax.swing.*;
import com.joyeria.view.InventarioView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuPrincipal = new JFrame("Sistema de Joyería");
            menuPrincipal.setSize(400, 200);
            menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuPrincipal.setLayout(new GridLayout(2, 1, 10, 10));
            menuPrincipal.setLocationRelativeTo(null);

            JButton botonInventario = new JButton("Inventario de Joyas");
            JButton botonVentas = new JButton("Registrar Venta");
            JButton botonHistorial = new JButton("Historial de Ventas");
            JButton botonReporte = new JButton("Reporte: Joyas Más Vendidas");

            botonInventario.addActionListener(e -> new InventarioView().setVisible(true));
            botonVentas.addActionListener(e -> new VentaView().setVisible(true));
            botonHistorial.addActionListener(e -> new HistorialVentasView().setVisible(true));
            botonReporte.addActionListener(e -> new ReporteJoyasMasVendidasView().setVisible(true));

            menuPrincipal.add(botonInventario);
            menuPrincipal.add(botonVentas);
            menuPrincipal.add(botonHistorial);
            menuPrincipal.add(botonReporte);

            menuPrincipal.setVisible(true);
        });
    }
}