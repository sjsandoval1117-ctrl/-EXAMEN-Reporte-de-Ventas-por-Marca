package dao;
import modelo.Celular;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {
    private final Connection con;
    public CelularDAO() { this.con = Conexion.getConexion(); }
    public List<Celular> listar() {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Celular c = new Celular(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("stock"), 10, 3);
                lista.add(c);
            }
        } catch(SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}