package com.joyeria.view;

import com.joyeria.controller.JoyaController;
import com.joyeria.model.JoyaVO;

import javax.swing.*;
import java.awt.*;

public class ActualizarJoyaDialog extends JDialog {

    private JTextField campoNombre;
    private JTextField campoMaterial;
    private JTextField campoPeso;
    private JTextField campoPrecio;
    private JTextField campoStock;
    private JButton botonActualizar;

    private InventarioView inventarioView;
    private JoyaController joyaController;
    private JoyaVO joyaExistente;

    public ActualizarJoyaDialog(InventarioView inventarioView, JoyaVO joyaExistente) {
        super(inventarioView, "Actualizar Joya", true);
        this.inventarioView = inventarioView;
        this.joyaExistente = joyaExistente;
        this.joyaController = new JoyaController();
        inicializarComponentes();
        cargarDatosExistentes();
    }

    private void inicializarComponentes() {
        setSize(400, 300);
        setLocationRelativeTo(inventarioView);
        setLayout(new GridLayout(6, 2, 5, 5));

        campoNombre = new JTextField();
        campoMaterial = new JTextField();
        campoPeso = new JTextField();
        campoPrecio = new JTextField();
        campoStock = new JTextField();
        botonActualizar = new JButton("Actualizar");

        add(new JLabel("Nombre:"));
        add(campoNombre);
        add(new JLabel("Material:"));
        add(campoMaterial);
        add(new JLabel("Peso (g):"));
        add(campoPeso);
        add(new JLabel("Precio ($):"));
        add(campoPrecio);
        add(new JLabel("Stock:"));
        add(campoStock);
        add(new JLabel(""));
        add(botonActualizar);

        botonActualizar.addActionListener(e -> actualizarJoya());
    }

    private void cargarDatosExistentes() {
        campoNombre.setText(joyaExistente.getNombre());
        campoMaterial.setText(joyaExistente.getMaterial());
        campoPeso.setText(String.valueOf(joyaExistente.getPeso()));
        campoPrecio.setText(String.valueOf(joyaExistente.getPrecio()));
        campoStock.setText(String.valueOf(joyaExistente.getStock()));
    }

    private void actualizarJoya() {
        if (campoNombre.getText().isEmpty() || campoMaterial.getText().isEmpty()
                || campoPeso.getText().isEmpty() || campoPrecio.getText().isEmpty()
                || campoStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            joyaExistente.setNombre(campoNombre.getText());
            joyaExistente.setMaterial(campoMaterial.getText());
            joyaExistente.setPeso(Double.parseDouble(campoPeso.getText()));
            joyaExistente.setPrecio(Double.parseDouble(campoPrecio.getText()));
            joyaExistente.setStock(Integer.parseInt(campoStock.getText()));

            if (joyaController.actualizarJoya(joyaExistente)) {
                JOptionPane.showMessageDialog(this, "Joya actualizada exitosamente.");
                inventarioView.actualizarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar joya.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Peso, precio y stock deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

