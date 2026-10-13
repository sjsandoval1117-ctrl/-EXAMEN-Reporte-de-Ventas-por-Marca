package dao;
import modelo.Persona;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private final Connection con;
    public PersonaDAO() { this.con = Conexion.getConexion(); }
    public List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Persona p = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("documento"), rs.getString("telefono"), rs.getString("correo")) {};
                lista.add(p);
            }
        } catch(SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}