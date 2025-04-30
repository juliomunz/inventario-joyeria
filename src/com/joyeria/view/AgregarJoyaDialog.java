package com.joyeria.view;

import com.joyeria.controller.JoyaController;
import com.joyeria.model.JoyaVO;

import javax.swing.*;
import java.awt.*;

public class AgregarJoyaDialog extends JDialog {

    private JTextField campoNombre;
    private JTextField campoMaterial;
    private JTextField campoPeso;
    private JTextField campoPrecio;
    private JTextField campoStock;
    private JButton botonGuardar;

    private InventarioView inventarioView;
    private JoyaController joyaController;

    public AgregarJoyaDialog(InventarioView inventarioView) {
        super(inventarioView, "Agregar Nueva Joya", true);
        this.inventarioView = inventarioView;
        this.joyaController = new JoyaController();
        inicializarComponentes();
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
        botonGuardar = new JButton("Guardar");

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
        add(botonGuardar);

        botonGuardar.addActionListener(e -> guardarJoya());
    }

    private void guardarJoya() {
        // Validaciones básicas
        if (campoNombre.getText().isEmpty() || campoMaterial.getText().isEmpty()
                || campoPeso.getText().isEmpty() || campoPrecio.getText().isEmpty()
                || campoStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = campoNombre.getText();
            String material = campoMaterial.getText();
            double peso = Double.parseDouble(campoPeso.getText());
            double precio = Double.parseDouble(campoPrecio.getText());
            int stock = Integer.parseInt(campoStock.getText());

            JoyaVO joya = new JoyaVO();
            joya.setNombre(nombre);
            joya.setMaterial(material);
            joya.setPeso(peso);
            joya.setPrecio(precio);
            joya.setStock(stock);

            if (joyaController.agregarJoya(joya)) {
                JOptionPane.showMessageDialog(this, "Joya agregada exitosamente.");
                inventarioView.actualizarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar joya.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Peso, precio y stock deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}