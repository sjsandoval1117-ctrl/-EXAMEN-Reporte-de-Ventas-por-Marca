package controlador;
import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class MenuController {
    private final ProductoDAO productoDAO;
    public MenuController() {
        this.productoDAO = new ProductoDAO();
    }
    public void mostrarMenu() {
        List<Producto> lista = productoDAO.listar();
        System.out.println("=== TECNOSTORE MENÚ PRINCIPAL ===");
        for(Producto p : lista) {
            System.out.println(p.getId() + " - " + p.getNombre() + " | Precio: $" + p.getPrecio());
        }
    }
}