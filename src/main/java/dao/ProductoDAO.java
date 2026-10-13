package dao;

import modelo.Producto;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final Connection con;

    public ProductoDAO() {
        this.con = Conexion.getConexion();
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch(SQLException e) { 
            System.out.println("Error al listar productos: " + e.getMessage()); 
        }
        return lista;
    }
}