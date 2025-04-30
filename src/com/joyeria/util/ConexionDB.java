package com.joyeria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/joyeria_db"; // Cambia a tu DB
    private static final String USER = "admin"; // Cambia al usuario de tu base de datos
    private static final String PASSWORD = "admin123"; // Cambia a tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

