package dao;
import persistencia.Conexion;
import java.sql.Connection;

public class TecnoStoreDAO {
    protected Connection con;
    public TecnoStoreDAO() {
        this.con = Conexion.getConexion();
    }
}