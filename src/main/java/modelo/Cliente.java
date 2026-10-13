package modelo;

public class Cliente extends Persona {
    private String categoriaCliente;

    public Cliente(int id, String nombre, String documento, String telefono, String correo, String categoriaCliente) {
        super(id, nombre, documento, telefono, correo);
        this.categoriaCliente = categoriaCliente;
    }

    public String getCategoriaCliente() { return categoriaCliente; }
    public void setCategoriaCliente(String categoriaCliente) { this.categoriaCliente = categoriaCliente; }
}