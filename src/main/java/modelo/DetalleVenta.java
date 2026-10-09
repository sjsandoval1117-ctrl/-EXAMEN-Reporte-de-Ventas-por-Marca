package modelo;

public class DetalleVenta {
<<<<<<< HEAD
    private int idCelular;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(int idCelular, int cantidad, double subtotal) {
        this.idCelular = idCelular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdCelular() { return idCelular; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}
=======

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
