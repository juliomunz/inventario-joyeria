package com.joyeria.view;

import com.joyeria.controller.JoyaController;
import com.joyeria.model.JoyaVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventarioView extends JFrame {

    private JTable tablaJoyas;
    private JButton botonAgregar;
    private JoyaController joyaController;
    private JButton botonActualizar;

    public InventarioView() {
        joyaController = new JoyaController();
        inicializarComponentes();
        cargarJoyasEnTabla();
    }

    private void inicializarComponentes() {
        setTitle("Inventario de Joyas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tablaJoyas = new JTable();
        botonAgregar = new JButton("Agregar Joya");
        botonAgregar.addActionListener(e -> {
            AgregarJoyaDialog dialog = new AgregarJoyaDialog(this);
            dialog.setVisible(true);
        });

        JButton botonActualizar = new JButton("Actualizar Joya");
        botonActualizar.addActionListener(e -> {
            int filaSeleccionada = tablaJoyas.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id = (int) tablaJoyas.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tablaJoyas.getValueAt(filaSeleccionada, 1);
                String material = (String) tablaJoyas.getValueAt(filaSeleccionada, 2);
                double peso = Double.parseDouble(tablaJoyas.getValueAt(filaSeleccionada, 3).toString());
                double precio = Double.parseDouble(tablaJoyas.getValueAt(filaSeleccionada, 4).toString());
                int stock = Integer.parseInt(tablaJoyas.getValueAt(filaSeleccionada, 5).toString());

                JoyaVO joyaSeleccionada = new JoyaVO(id, nombre, material, peso, precio, stock);

                ActualizarJoyaDialog dialog = new ActualizarJoyaDialog(this, joyaSeleccionada);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una joya para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonEliminar = new JButton("Eliminar Joya");
        botonEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaJoyas.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta joya?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    int id = (int) tablaJoyas.getValueAt(filaSeleccionada, 0);
                    if (joyaController.eliminarJoya(id)) {
                        JOptionPane.showMessageDialog(this, "Joya eliminada exitosamente.");
                        actualizarTabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar la joya.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una joya para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaJoyas);

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonAgregar);
        panelBoton.add(botonActualizar);
        panelBoton.add(botonEliminar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarJoyasEnTabla() {
        List<JoyaVO> listaJoyas = joyaController.obtenerTodasLasJoyas();

        String[] columnas = {"ID", "Nombre", "Material", "Peso", "Precio", "Stock"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        for (JoyaVO joya : listaJoyas) {
            Object[] fila = {
                    joya.getId(),
                    joya.getNombre(),
                    joya.getMaterial(),
                    joya.getPeso(),
                    joya.getPrecio(),
                    joya.getStock()
            };
            modeloTabla.addRow(fila);
        }

        tablaJoyas.setModel(modeloTabla);
    }

    public JTable getTablaJoyas() {
        return tablaJoyas;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public void actualizarTabla() {
        cargarJoyasEnTabla();
    }
}

