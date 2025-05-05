package com.joyeria.Main;
import com.joyeria.view.*;

import javax.swing.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuPrincipal = new JFrame("Sistema de Joyería");
            menuPrincipal.setSize(400, 200);
            menuPrincipal.setLayout(new GridLayout(2, 1, 10, 10));
            menuPrincipal.setLocationRelativeTo(null);

            JButton botonInventario = new JButton("Inventario de Joyas");
            JButton botonVentas = new JButton("Registrar Venta");
            JButton botonHistorial = new JButton("Historial de Ventas");
            JButton botonReporte = new JButton("Reporte: Joyas Más Vendidas");
            JButton botonClientes = new JButton("Gestionar Clientes");
            JButton botonSalir = new JButton("Salir");
            botonSalir.addActionListener(e -> {
                int respuesta = JOptionPane.showConfirmDialog(menuPrincipal, "¿Está seguro de que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });

            botonInventario.addActionListener(e -> new InventarioView().setVisible(true));
            botonVentas.addActionListener(e -> new VentaView().setVisible(true));
            botonHistorial.addActionListener(e -> new HistorialVentasView().setVisible(true));
            botonReporte.addActionListener(e -> new ReporteJoyasMasVendidasView().setVisible(true));
            botonClientes.addActionListener(e -> {
                        ClienteView vistaClientes = new ClienteView();
                        vistaClientes.setVisible(true);
                    });

            menuPrincipal.add(botonClientes);
            menuPrincipal.add(botonInventario);
            menuPrincipal.add(botonVentas);
            menuPrincipal.add(botonHistorial);
            menuPrincipal.add(botonReporte);
            menuPrincipal.add(botonSalir);

            menuPrincipal.setVisible(true);
        });
    }
}