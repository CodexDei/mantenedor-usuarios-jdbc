package org.codexdei.util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/java_curso";
    private static Connection conexion;

    public static Connection generarConexion() throws SQLException {

        while (conexion == null || conexion.isClosed()) {
            String usuario = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario:", "Conexión a Base de Datos", JOptionPane.PLAIN_MESSAGE);
            String password = new String(JOptionPane.showInputDialog(null, "Ingrese la contraseña:", "Conexión a Base de Datos", JOptionPane.PLAIN_MESSAGE).toCharArray());

            try {
                conexion = DriverManager.getConnection(URL, usuario, password);
                JOptionPane.showMessageDialog(null, "Conexión exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Credenciales inválidas. Inténtelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return conexion;
    }
}
