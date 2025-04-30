package com.joyeria.dao;

import com.joyeria.model.ClienteVO;
import com.joyeria.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Agregar nuevo cliente
    public boolean agregarCliente(ClienteVO cliente) {
        String sql = "INSERT INTO cliente (nombre) VALUES (?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos los clientes
    public List<ClienteVO> listarClientes() {
        List<ClienteVO> listaClientes = new ArrayList<>();
        String sql = "SELECT id, nombre FROM cliente";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClienteVO cliente = new ClienteVO();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        }

        return listaClientes;
    }
}