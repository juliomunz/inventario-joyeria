package com.joyeria.controller;

import com.joyeria.dao.JoyaDAO;
import com.joyeria.model.JoyaVO;

import java.util.List;

public class JoyaController {

    private JoyaDAO joyaDAO;

    public JoyaController() {
        this.joyaDAO = new JoyaDAO();
    }

    public List<JoyaVO> obtenerTodasLasJoyas() {
        return joyaDAO.listarJoyas();
    }

    public boolean agregarJoya(JoyaVO joya) {
        return joyaDAO.agregarJoya(joya);
    }

    public boolean actualizarJoya(JoyaVO joya) {
        return joyaDAO.actualizarJoya(joya);
    }

    public boolean eliminarJoya(int id) {
        return joyaDAO.eliminarJoya(id);
    }
}