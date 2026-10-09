package com.s2.projectstreamapi;

import dao.TecnoStoreDAO;
import modelo.*;
import model.Vendedor;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TecnoStoreDAO dao = new TecnoStoreDAO();

        System.out.println("========================================");
        System.out.println("   BIENVENIDO A TECNOSTORE - SISTEMA   ");
        System.out.println("========================================");

        System.out.print(
                "Ingrese su número de identificación (cédula): "
        );

        String identificacion =
                scanner.nextLine();

        Persona persona =
                dao.validarUsuario(identificacion);

        if (persona == null) {

            System.out.println(
                    "Usuario no encontrado en la base de datos."
            );

            scanner.close();
            return;
        }

        System.out.println(
                "\n¡Bienvenido, "
                + persona.getNombre()
                + "!"
        );

        persona.mostrarRol();

        if (persona instanceof Vendedor) {

            menuVendedor(scanner, dao);

        } else if (persona instanceof Cliente cliente) {

            menuCliente(scanner, cliente, dao);
        }

        scanner.close();
    }

    private static void menuVendedor(
            Scanner scanner,
            TecnoStoreDAO dao) {

        int opcion;

        do {

            System.out.println("\n--- MENÚ VENDEDOR ---");
            System.out.println(
                    "1. Ver inventario completo"
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

            System.out.print(
                    "Seleccione una opción: "
            );

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> {

                    List<Producto> productos =
                            dao.obtenerProductos();

                    for (Producto p : productos) {
                        System.out.println(p);
                    }
                }

                case 2 -> {

                    System.out.print(
                            "Ingrese la marca: "
                    );

                    String marca =
                            scanner.nextLine();

                    List<Producto> productos =
                            dao.filtrarPorMarca(marca);

                    if (productos.isEmpty()) {

                        System.out.println(
                                "No se encontraron productos."
                        );

                    } else {

                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                }

                case 3 ->
                        dao.exportarResumenVentasTxt();

                case 4 ->
                        System.out.println(
                                "Saliendo del sistema..."
                        );

                default ->
                        System.out.println(
                                "Opción inválida."
                        );
            }

        } while (opcion != 4);
    }

    private static void menuCliente(
            Scanner scanner,
            Cliente cliente,
            TecnoStoreDAO dao) {

        int opcion;

        do {

            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println(
                    "1. Ver dispositivos disponibles"
            );
            System.out.println(
                    "2. Comprar producto"
            );
            System.out.println(
                    "3. Filtrar productos por marca"
            );
            System.out.println(
                    "4. Ver carrito"
            );
            System.out.println(
                    "5. Confirmar compra"
            );
            System.out.println(
                    "6. Salir"
            );

            System.out.print(
                    "Seleccione una opción: "
            );

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 ->
                        mostrarProductos(dao);

                case 2 ->
                        comprarProducto(
                                scanner,
                                cliente,
                                dao
                        );

                case 3 -> {

                    System.out.print(
                            "Ingrese la marca: "
                    );

                    String marca =
                            scanner.nextLine();

                    List<Producto> productos =
                            dao.filtrarPorMarca(marca);

                    if (productos.isEmpty()) {

                        System.out.println(
                                "No se encontraron productos."
                        );

                    } else {

                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                }

                case 4 ->
                        mostrarCarrito(cliente);

                case 5 ->
                        confirmarCompra(
                                scanner,
                                cliente,
                                dao
                        );

                case 6 ->
                        System.out.println(
                                "¡Gracias por visitar TecnoStore!"
                        );

                default ->
                        System.out.println(
                                "Opción inválida."
                        );
            }

        } while (opcion != 6);
    }

    private static void mostrarProductos(
            TecnoStoreDAO dao) {

        List<Producto> productos =
                dao.obtenerProductos();

        System.out.println(
                "\n--- DISPOSITIVOS DISPONIBLES ---"
        );

        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    private static void comprarProducto(
            Scanner scanner,
            Cliente cliente,
            TecnoStoreDAO dao) {

        mostrarProductos(dao);

        System.out.print(
                "\nIngrese el SKU del producto: "
        );

        String sku =
                scanner.nextLine();

        Producto producto =
                dao.buscarPorSku(sku);

        if (producto == null) {

            System.out.println(
                    "Producto no encontrado."
            );

            return;
        }

        System.out.println(
                "\nProducto: "
                + producto.getNombre()
        );

        System.out.println(
                "Precio: $"
                + producto.getPrecio()
        );

        System.out.println(
                "Stock disponible: "
                + producto.getStock()
        );

        if (producto.getStock() <= 0) {

            System.out.println(
                    "Producto agotado."
            );

            return;
        }

        System.out.print(
                "Ingrese la cantidad: "
        );

        int cantidad =
                scanner.nextInt();

        scanner.nextLine();

        if (cantidad <= 0) {

            System.out.println(
                    "La cantidad debe ser mayor que cero."
            );

            return;
        }

        if (cantidad > producto.getStock()) {

            System.out.println(
                    "No hay suficiente stock."
            );

            return;
        }

        DetalleVenta detalle =
                new DetalleVenta(
                        producto,
                        cantidad
                );

        cliente.agregarAlCarrito(detalle);

        System.out.println(
                "\nProducto agregado al carrito."
        );

        System.out.println(
                "Subtotal: $"
                + detalle.getSubtotal()
        );
    }

    private static void mostrarCarrito(
            Cliente cliente) {

        System.out.println(
                "\n--- CARRITO DE COMPRAS ---"
        );

        if (cliente.getCarritoCompras().isEmpty()) {

            System.out.println(
                    "El carrito está vacío."
            );

            return;
        }

        for (DetalleVenta detalle :
                cliente.getCarritoCompras()) {

            System.out.println(
                    "SKU: "
                    + detalle.getProducto().getSku()
            );

            System.out.println(
                    "Producto: "
                    + detalle.getProducto().getNombre()
            );

            System.out.println(
                    "Cantidad: "
                    + detalle.getCantidad()
            );

            System.out.println(
                    "Subtotal: $"
                    + detalle.getSubtotal()
            );

            System.out.println(
                    "--------------------------------"
            );
        }

        System.out.println(
                "TOTAL: $"
                + cliente.calcularTotal()
        );
    }

    private static void confirmarCompra(
            Scanner scanner,
            Cliente cliente,
            TecnoStoreDAO dao) {

        if (cliente.getCarritoCompras().isEmpty()) {

            System.out.println(
                    "El carrito está vacío."
            );

            return;
        }

        mostrarCarrito(cliente);

        System.out.print(
                "\n¿Desea confirmar la compra? (S/N): "
        );

        String respuesta =
                scanner.nextLine();

        if (!respuesta.equalsIgnoreCase("S")) {

            System.out.println(
                    "Compra cancelada."
            );

            return;
        }

        boolean compra =
                dao.confirmarCompra(cliente);

        if (compra) {

            System.out.println(
                    "\n========================================"
            );

            System.out.println(
                    "       COMPRA REALIZADA CON ÉXITO"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "Total pagado correctamente."
            );

        } else {

            System.out.println(
                    "No se pudo realizar la compra."
            );
        }
    }
}