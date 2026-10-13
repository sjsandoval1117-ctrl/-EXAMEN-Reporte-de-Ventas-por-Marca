package dao;
import modelo.Vendedor;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    private final Connection con;
    public VendedorDAO() { this.con = Conexion.getConexion(); }
    public List<Vendedor> listar() {
        List<Vendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Vendedor v = new Vendedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("documento"), rs.getString("telefono"), rs.getString("correo"), rs.getString("codigo_vendedor"));
                lista.add(v);
            }
        } catch(SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}