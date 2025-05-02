package com.joyeria.dao;

import com.joyeria.model.JoyaVO;
import com.joyeria.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoyaDAO {


    public List<JoyaVO> listarJoyas() {
        List<JoyaVO> listaJoyas = new ArrayList<>();
        String sql = "SELECT id, nombre, material, peso, precio, stock FROM joya";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JoyaVO joya = new JoyaVO();
                joya.setId(rs.getInt("id"));
                joya.setNombre(rs.getString("nombre"));
                joya.setMaterial(rs.getString("material"));
                joya.setPeso(rs.getDouble("peso"));
                joya.setPrecio(rs.getDouble("precio"));
                joya.setStock(rs.getInt("stock"));
                listaJoyas.add(joya);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar joyas: " + e.getMessage());
            e.printStackTrace();
        }

        return listaJoyas;
    }

    public boolean agregarJoya(JoyaVO joya) {
        String sql = "INSERT INTO joya (nombre, material, peso, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, joya.getNombre());
            stmt.setString(2, joya.getMaterial());
            stmt.setDouble(3, joya.getPeso());
            stmt.setDouble(4, joya.getPrecio());
            stmt.setInt(5, joya.getStock());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar joya: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarJoya(JoyaVO joya) {
        String sql = "UPDATE joya SET nombre = ?, material = ?, peso = ?, precio = ?, stock = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, joya.getNombre());
            stmt.setString(2, joya.getMaterial());
            stmt.setDouble(3, joya.getPeso());
            stmt.setDouble(4, joya.getPrecio());
            stmt.setInt(5, joya.getStock());
            stmt.setInt(6, joya.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar joya: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarJoya(int id) {
        String sql = "DELETE FROM joya WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar joya: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

