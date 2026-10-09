package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;
    private final String URL = "jdbc:mysql://localhost:3306/celulares_tecnostore";
    private final String USER = "root";
    private final String PASS = "Sara1097497675*"; // Cambia tu contraseña si tienes una en MySQL

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la Base de Datos: " + e.getMessage());
        }
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        try {
            if (instancia.conexion == null || instancia.conexion.isClosed()) {
                instancia = new Conexion();
            }
        } catch (SQLException e) {
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}