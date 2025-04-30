package com.joyeria.view;

import com.joyeria.controller.ClienteController;
import com.joyeria.model.ClienteVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JFrame {

    private JTextField campoNombre;
    private JButton botonAgregar;
    private JTable tablaClientes;
    private ClienteController clienteController;

    public ClienteView() {
        clienteController = new ClienteController();
        inicializarComponentes();
        cargarClientesEnTabla();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Clientes");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        campoNombre = new JTextField();
        botonAgregar = new JButton("Agregar Cliente");

        panelFormulario.add(new JLabel("Nombre Cliente:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(botonAgregar);

        tablaClientes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        botonAgregar.addActionListener(e -> agregarCliente());
    }

    private void agregarCliente() {
        if (campoNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClienteVO cliente = new ClienteVO();
        cliente.setNombre(campoNombre.getText());

        if (clienteController.agregarCliente(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
            campoNombre.setText("");
            cargarClientesEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarClientesEnTabla() {
        List<ClienteVO> clientes = clienteController.obtenerTodosLosClientes();

        String[] columnas = {"ID Cliente", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (ClienteVO cliente : clientes) {
            Object[] fila = {cliente.getId(), cliente.getNombre()};
            modelo.addRow(fila);
        }

        tablaClientes.setModel(modelo);
    }
}