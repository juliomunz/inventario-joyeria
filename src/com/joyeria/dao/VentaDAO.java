package com.joyeria.dao;

import com.joyeria.model.VentaVO;
import com.joyeria.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // Método para registrar una venta
    public boolean registrarVenta(VentaVO venta) {
        String sql = "INSERT INTO venta (id_cliente, id_joya, cantidad, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venta.getIdCliente());
            stmt.setInt(2, venta.getIdJoya());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDate(4, java.sql.Date.valueOf(venta.getFecha()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar venta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todas las ventas
    public List<Object[]> listarVentasConNombreJoya() {
        List<Object[]> listaVentas = new ArrayList<>();
        String sql = "SELECT v.id, v.id_cliente, j.nombre AS nombre_joya, v.cantidad, v.fecha " +
                "FROM venta v " +
                "JOIN joya j ON v.id_joya = j.id " +
                "ORDER BY v.fecha DESC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_joya"),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha").toLocalDate()
                };
                listaVentas.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ventas con nombre de joya: " + e.getMessage());
            e.printStackTrace();
        }

        return listaVentas;
    }

    // Método para listar joyas más vendidas
    public List<Object[]> obtenerJoyasMasVendidasConNombre() {
        List<Object[]> resultados = new ArrayList<>();
        String sql = "SELECT j.nombre AS nombre_joya, SUM(v.cantidad) AS total_vendido " +
                "FROM venta v " +
                "JOIN joya j ON v.id_joya = j.id " +
                "GROUP BY j.nombre " +
                "ORDER BY total_vendido DESC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getString("nombre_joya"),
                        rs.getInt("total_vendido")
                };
                resultados.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener joyas más vendidas con nombre: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
    }
}