package com.joyeria.controller;

import com.joyeria.dao.ClienteDAO;
import com.joyeria.model.ClienteVO;

import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean agregarCliente(ClienteVO cliente) {
        return clienteDAO.agregarCliente(cliente);
    }

    public List<ClienteVO> obtenerTodosLosClientes() {
        return clienteDAO.listarClientes();
    }
}