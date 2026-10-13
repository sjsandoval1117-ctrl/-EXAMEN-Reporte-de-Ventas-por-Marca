package modelo;

public class Vendedor extends Persona {
    private String codigoVendedor;

    public Vendedor(int id, String nombre, String documento, String telefono, String correo, String codigoVendedor) {
        super(id, nombre, documento, telefono, correo);
        this.codigoVendedor = codigoVendedor;
    }

    public String getCodigoVendedor() { return codigoVendedor; }
    public void setCodigoVendedor(String codigoVendedor) { this.codigoVendedor = codigoVendedor; }
}