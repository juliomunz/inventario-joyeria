package com.joyeria.view;

import com.joyeria.controller.ClienteController;
import com.joyeria.controller.JoyaController;
import com.joyeria.controller.VentaController;
import com.joyeria.model.ClienteVO;
import com.joyeria.model.JoyaVO;
import com.joyeria.model.VentaVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class VentaView extends JFrame {

    private JComboBox<JoyaVO> comboJoyas;
    private JComboBox<ClienteVO> comboClientes;
    private JTextField campoCantidad;
    private JButton botonVender;
    private VentaController ventaController;
    private JoyaController joyaController;

    public VentaView() {
        ventaController = new VentaController();
        joyaController = new JoyaController();
        inicializarComponentes();
        cargarJoyasEnComboBox();
        cargarClientesEnComboBox();
    }

    private void inicializarComponentes() {
        setTitle("Registro de Ventas");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        comboJoyas = new JComboBox<>();
        comboClientes = new JComboBox<>();
        campoCantidad = new JTextField();
        botonVender = new JButton("Realizar Venta");


        add(new JLabel("Seleccione Joya:"));
        add(comboJoyas);
        add(new JLabel("Cantidad:"));
        add(campoCantidad);
        add(new JLabel(""));
        add(botonVender);
        add(new JLabel("Seleccione Cliente:"));
        add(comboClientes);

        botonVender.addActionListener(e -> realizarVenta());
    }

    private void cargarJoyasEnComboBox() {
        List<JoyaVO> joyas = joyaController.obtenerTodasLasJoyas();
        for (JoyaVO joya : joyas) {
            comboJoyas.addItem(joya);
        }
    }

    private void cargarClientesEnComboBox() {
        List<ClienteVO> clientes = new ClienteController().obtenerTodosLosClientes();
        comboClientes.removeAllItems();
        for (ClienteVO cliente : clientes) {
            comboClientes.addItem(cliente);
        }
    }

    private void realizarVenta() {
        JoyaVO joyaSeleccionada = (JoyaVO) comboJoyas.getSelectedItem();
        ClienteVO clienteSeleccionado = (ClienteVO) comboClientes.getSelectedItem();

        if (joyaSeleccionada == null || clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una joya.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cantidadVenta = Integer.parseInt(campoCantidad.getText());

            if (cantidadVenta <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidadVenta > joyaSeleccionada.getStock()) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente para la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear la venta
            VentaVO venta = new VentaVO();
            venta.setIdCliente(clienteSeleccionado.getId());
            venta.setIdJoya(joyaSeleccionada.getId());
            venta.setCantidad(cantidadVenta);
            venta.setFecha(LocalDate.now());

            if (ventaController.registrarVenta(venta)) {
                // Actualizar stock de la joya
                joyaSeleccionada.setStock(joyaSeleccionada.getStock() - cantidadVenta);
                joyaController.actualizarJoya(joyaSeleccionada);

                JOptionPane.showMessageDialog(this, "Venta realizada exitosamente.");
                campoCantidad.setText("");
                cargarJoyasEnComboBox(); // refresca los datos del ComboBox
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numérica.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}