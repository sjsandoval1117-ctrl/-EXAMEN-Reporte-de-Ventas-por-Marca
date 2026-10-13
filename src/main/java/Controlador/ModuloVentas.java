package Controlador;

import modelo.Celular;
import modelo.DetalleVenta;
import utilidades.ArchivoUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuloVentas {
    private final List<Celular> inventarioMemoria = new ArrayList<>();
    private final List<DetalleVenta> carritoActual = new ArrayList<>();

    public ModuloVentas() {
        inventarioMemoria.add(new Celular(1, "Samsung", "Galaxy S25 Ultra", 4999900.0, 10));
        inventarioMemoria.add(new Celular(2, "Apple", "iPhone 14", 3500000.0, 5));
        inventarioMemoria.add(new Celular(3, "Xiaomi", "Redmi Note 13", 1200000.0, 8));
        inventarioMemoria.add(new Celular(4, "Motorola", "Moto G67 5G", 987900.0, 12));
        inventarioMemoria.add(new Celular(5, "Infinix", "Note 60 5G", 1149900.0, 15));
        inventarioMemoria.add(new Celular(6, "Realme", "12 Pro+", 1850000.0, 7));
    }

    public List<Celular> getInventarioMemoria() {
        return inventarioMemoria;
    }

    public void agregarAlInventario(Celular c) {
        if (c != null) {
            inventarioMemoria.add(c);
        }
    }

    public List<Celular> filtrarPorMarca(String marcaBusqueda) {
        if (marcaBusqueda == null || marcaBusqueda.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String busquedaLimpia = marcaBusqueda.trim().toLowerCase();
        
        return inventarioMemoria.stream()
            .filter(c -> c != null && c.getMarca() != null)
            .filter(c -> c.getMarca().trim().toLowerCase().contains(busquedaLimpia))
            .collect(Collectors.toList());
    }

    public void agregarAlCarrito(Celular celular, int cantidad) throws Exception {
        if (!celular.verificarStock(cantidad)) {
            throw new Exception("Error: Stock insuficiente. Solo quedan " + celular.getStock() + " unidades.");
        }
        carritoActual.add(new DetalleVenta(celular, cantidad));
        System.out.println("Producto agregado al carrito temporal con éxito.");
    }

    public void finalizarVentaYGenerarReporte() {
        double totalGeneral = carritoActual.stream()
            .mapToDouble(DetalleVenta::getSubtotal)
            .sum();

        StringBuilder sb = new StringBuilder();
        sb.append("==== RESUMEN DE VENTAS TOTALES TECNOSTORE ====\n");
        for (DetalleVenta dv : carritoActual) {
            sb.append("- ").append(dv.getCelular().getMarca()).append(" ").append(dv.getCelular().getModelo())
              .append(" | Cantidad: ").append(dv.getCantidad())
              .append(" | Subtotal: $").append(dv.getSubtotal()).append("\n");
        }
        sb.append("TOTAL GENERAL: $").append(totalGeneral).append("\n");
        sb.append("==============================================\n");

        ArchivoUtils.guardarTexto("ResumenVentasTecnoStore.txt", sb.toString());
        System.out.println("Resumen de ventas exportado a 'ResumenVentasTecnoStore.txt'.");

        carritoActual.clear();
    }
}