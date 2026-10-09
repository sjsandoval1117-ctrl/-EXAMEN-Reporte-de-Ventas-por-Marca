package dao;

import model.Telefono;
import model.categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class telefonoDAO {

    // Configuración de tu conexión a MySQL
    private final String URL = "jdbc:mysql://localhost:3306/projectstreamapi";
    private final String USER = "root";
    private final String PASSWORD = "Sara1097497675*"; // Cámbiala si tu MySQL tiene contraseña

    // Obtener todos los teléfonos directamente desde la base de datos MySQL
    public List<Telefono> obtenerTodos() {
        List<Telefono> listaTelefonos = new ArrayList<>();
        
        String sql = "SELECT t.id, t.marca, t.precio, c.nombre AS categoria_nombre " +
                     "FROM telefono t JOIN categoria c ON t.categoria_id = c.id";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idTelefono = rs.getInt("id");
                String marca = rs.getString("marca");
                double precio = rs.getDouble("precio");
                String nombreCategoria = rs.getString("categoria_nombre");

                // Consultar las funcionalidades asociadas a este teléfono en la BD
                List<String> funcionalidades = obtenerFuncionalidadesPorId(conexion, idTelefono);

                // Crear el objeto y agregarlo a la lista
                Telefono telefono = new Telefono(marca, precio, nombreCategoria, funcionalidades);
                listaTelefonos.add(telefono);
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        }

        return listaTelefonos;
    }

    // Método auxiliar para buscar las funcionalidades de cada teléfono en la BD
    private List<String> obtenerFuncionalidadesPorId(Connection conexion, int telefonoId) {
        List<String> funcionalidades = new ArrayList<>();
        String sql = "SELECT funcionalidad FROM telefono_funcionalidad WHERE telefono_id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, telefonoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    funcionalidades.add(rs.getString("funcionalidad"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener funcionalidades: " + e.getMessage());
        }

        return funcionalidades;
    }

    // --- MÉTODOS CON STREAM API (Funcionan sobre los datos extraídos de MySQL) ---

    public List<Telefono> filtrarPorPrecioMaximo(double precioMax) {
        return obtenerTodos().stream()
                .filter(t -> t.getPrecio() <= precioMax)
                .toList();
    }

    public List<Telefono> filtrarPorMarca(String marca) {
        return obtenerTodos().stream()
                .filter(t -> t.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    public List<Telefono> filtrarPorCategoria(String nombreCategoria) {
        return obtenerTodos().stream()
                .filter(t -> t.getCategoria().getNombre().equalsIgnoreCase(nombreCategoria))
                .toList();
    }

    public Map<String, List<Telefono>> agruparPorCategoria() {
        return obtenerTodos().stream()
                .collect(Collectors.groupingBy(t -> t.getCategoria().getNombre()));
    }
}