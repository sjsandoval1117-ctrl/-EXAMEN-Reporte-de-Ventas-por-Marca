package dao;

import modelo.Producto;
import persistencia.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {

    public List<Producto> obtenerCelulares() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try (Connection conn = Conexion.getInstancia().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener celulares: " + e.getMessage());
        }
        return lista;
    }

    public Producto buscarPorSku(String sku) {
        String sql = "SELECT * FROM celulares WHERE sku = ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sku);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar celular por SKU: " + e.getMessage());
        }
        return null;
    }

    public List<Producto> filtrarPorMarca(String marca) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares WHERE marca LIKE ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + marca + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar celulares por marca: " + e.getMessage());
        }
        return lista;
    }
}