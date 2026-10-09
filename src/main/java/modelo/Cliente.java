package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
<<<<<<< HEAD
    private List<DetalleCarrito> carritoCompras;
=======

    private final List<DetalleVenta> carritoCompras;
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910

    public Cliente(String identificacion, String nombre, String correo, String telefono) {
        super(identificacion, nombre, correo, telefono);
        this.carritoCompras = new ArrayList<>();
    }

<<<<<<< HEAD
    @Override
    public void mostrarRol() {
        System.out.println("Rol en el sistema: CLIENTE");
    }

    public void agregarAlCarrito(Producto producto, int cantidad) {
        for (DetalleCarrito item : carritoCompras) {
            if (item.getProducto().getId() == producto.getId()) {
                item.setCantidad(item.getCantidad() + cantidad);
                item.setSubtotal(item.getCantidad() * producto.getPrecio());
                return;
            }
        }
        carritoCompras.add(new DetalleCarrito(producto, cantidad));
    }

    public List<DetalleCarrito> getCarritoCompras() {
        return carritoCompras;
    }

    public double calcularTotal() {
        double subtotalGeneral = carritoCompras.stream().mapToDouble(DetalleCarrito::getSubtotal).sum();
        return subtotalGeneral * 1.19; // IVA del 19%
=======
    public List<DetalleVenta> getCarritoCompras() {
        return carritoCompras;
    }

    public void agregarAlCarrito(DetalleVenta detalle) {
        carritoCompras.add(detalle);
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
    }

    public void limpiarCarrito() {
        carritoCompras.clear();
    }
<<<<<<< HEAD
}
=======

    public double calcularTotal() {
        return carritoCompras.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente (Compra de dispositivos)");
    }
}
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
