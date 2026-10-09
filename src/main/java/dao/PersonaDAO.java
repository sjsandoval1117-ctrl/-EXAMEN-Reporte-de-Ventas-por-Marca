package dao;

import modelo.Cliente;
import modelo.Persona;
import modelo.Vendedor;
import persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {

    public Persona validarUsuario(String identificacion) {
        String sql = "SELECT * FROM personas WHERE identificacion = ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo_persona");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");

                if ("CLIENTE".equalsIgnoreCase(tipo)) {
                    return new Cliente(identificacion, nombre, correo, telefono);
                } else if ("VENDEDOR".equalsIgnoreCase(tipo)) {
                    return new Vendedor(identificacion, nombre, correo, telefono);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }
        return null;
    }
}
