package controlador;

import dao.TecnoStoreDAO;
import modelo.Cliente;
import modelo.Persona;
import modelo.Producto;
import modelo.Vendedor;
import util.validaciones;

import java.util.List;

public class MenuController {

    private final TecnoStoreDAO dao;
    private final validaciones validar;

    public MenuController() {

        dao = new TecnoStoreDAO();
        validar = new validaciones();
    }

    public void iniciarSistema() {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "       BIENVENIDO A TECNOSTORE"
        );

        System.out.println(
                "========================================"
        );

        String identificacion =
                validar.texto(
                        "Ingrese su número de identificación: "
                );

        Persona persona =
                dao.validarUsuario(
                        identificacion
                );

        if (persona == null) {

            System.out.println(
                    "Usuario no encontrado."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Bienvenido, "
                + persona.getNombre()
        );

        persona.mostrarRol();

        if (persona instanceof Cliente cliente) {

            menuCliente(cliente);

        } else if (persona instanceof Vendedor) {

            menuVendedor();
        }
    }

    private void menuCliente(
            Cliente cliente) {

        int opcion;

        do {

            System.out.println();
            System.out.println(
                    "========================================"
            );
            System.out.println(
                    "             MENÚ CLIENTE"
            );
            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Ver productos"
            );

            System.out.println(
                    "2. Comprar producto"
            );

            System.out.println(
                    "3. Ver carrito"
            );

            System.out.println(
                    "4. Confirmar compra"
            );

            System.out.println(
                    "5. Salir"
            );

            opcion =
                    validar.menu(
                            "Seleccione una opción: ",
                            1,
                            5
                    );

            switch (opcion) {

                case 1 -> mostrarProductos();

                case 2 -> comprarProducto(cliente);

                case 3 -> mostrarCarrito(cliente);

                case 4 -> confirmarCompra(cliente);

                case 5 -> System.out.println(
                            "Saliendo del sistema..."
                    );
            }

        } while (opcion != 5);
    }

    private void menuVendedor() {

        int opcion;

        do {

            System.out.println();
            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "            MENÚ VENDEDOR"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Ver inventario"
            );

            System.out.println(
                    "2. Filtrar productos por marca"
            );

            System.out.println(
                    "3. Generar reporte de ventas"
            );

            System.out.println(
                    "4. Salir"
            );

            opcion =
                    validar.menu(
                            "Seleccione una opción: ",
                            1,
                            4
                    );

            switch (opcion) {

                case 1:
                    mostrarProductos();
                    break;

                case 2:
                    filtrarMarca();
                    break;

                case 3:
                    dao.exportarResumenVentasTxt();
                    break;

                case 4:
                    System.out.println(
                            "Saliendo del sistema..."
                    );
                    break;
            }

        } while (opcion != 4);
    }

    private void mostrarProductos() {

        List<Producto> productos =
                dao.obtenerProductos();

        System.out.println();

        System.out.println(
                "========== PRODUCTOS =========="
        );

        if (productos.isEmpty()) {

            System.out.println(
                    "No hay productos registrados."
            );

            return;
        }

        for (Producto producto :
                productos) {

            System.out.println(producto);
        }
    }

    private void filtrarMarca() {

        String marca =
                validar.texto(
                        "Ingrese la marca: "
                );

        List<Producto> productos =
                dao.filtrarPorMarca(marca);

        System.out.println();

        if (productos.isEmpty()) {

            System.out.println(
                    "No se encontraron productos."
            );

            return;
        }

        for (Producto producto :
                productos) {

            System.out.println(producto);
        }
    }

    private void comprarProducto(
            Cliente cliente) {

        String sku =
                validar.texto(
                        "Ingrese el SKU del producto: "
                );

        Producto producto =
                dao.buscarPorSku(sku);

        if (producto == null) {

            System.out.println(
                    "Producto no encontrado."
            );

            return;
        }

        System.out.println();
        System.out.println(producto);

        if (producto.getStock() <= 0) {

            System.out.println(
                    "El producto no tiene stock."
            );

            return;
        }

        int cantidad =
                validar.stock(
                        "Ingrese la cantidad: ",
                        1,
                        producto.getStock()
                );

        cliente.agregarAlCarrito(
                producto,
                cantidad
        );

        System.out.println(
                "Producto agregado al carrito."
        );
    }

    private void mostrarCarrito(
            Cliente cliente) {

        System.out.println();

        System.out.println(
                "========== CARRITO =========="
        );

        if (cliente
                .getCarritoCompras()
                .isEmpty()) {

            System.out.println(
                    "El carrito está vacío."
            );

            return;
        }

        for (var detalle :
                cliente.getCarritoCompras()) {

            System.out.println(
                    detalle.getProducto()
                    .getNombre()
                    + " | Cantidad: "
                    + detalle.getCantidad()
                    + " | Subtotal: $"
                    + detalle.getSubtotal()
            );
        }

        System.out.println(
                "TOTAL: $"
                + cliente.calcularTotal()
        );
    }

    private void confirmarCompra(
            Cliente cliente) {

        if (cliente
                .getCarritoCompras()
                .isEmpty()) {

            System.out.println(
                    "El carrito está vacío."
            );

            return;
        }

        mostrarCarrito(cliente);

        String respuesta =
                validar.texto(
                        "¿Desea confirmar la compra? SI/NO: "
                );

        if (respuesta.equalsIgnoreCase("SI")) {

            boolean resultado =
                    dao.confirmarCompra(cliente);

            if (resultado) {

                System.out.println(
                        "Compra realizada correctamente."
                );

            }

        } else {

            System.out.println(
                    "Compra cancelada."
            );
        }
    }
}