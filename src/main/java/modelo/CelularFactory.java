package modelo;

public class CelularFactory {
    
    public static Celular crearCelular(int id, String marca, String modelo, double precio, int stock) {
        return new Celular(id, marca, modelo, precio, stock);
    }
}