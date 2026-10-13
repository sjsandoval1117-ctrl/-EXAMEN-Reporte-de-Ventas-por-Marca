package dao;
import modelo.Cliente;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection con;
    public ClienteDAO() { this.con = Conexion.getConexion(); }
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Cliente c = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("documento"), rs.getString("telefono"), rs.getString("correo"), rs.getString("categoria"));
                lista.add(c);
            }
        } catch(SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}