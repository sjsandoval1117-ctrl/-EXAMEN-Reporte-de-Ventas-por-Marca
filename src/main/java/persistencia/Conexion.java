package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection con = null;
    public static Connection getConexion() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/tecnostore";
                String user = "root";
                String password = "Sara1097497675*";
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Error de conexión: " + e.getMessage());
            }
        }
        return con;
    }
}