package modelo;

public class DetalleVenta {
    private Celular celular;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Celular celular, int cantidad) {
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

    public Celular getCelular() { return celular; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}