package dao;

<<<<<<< HEAD
import modelo.*;
import persistencia.Conexion;
import utilidades.ArchivoUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
=======
import conexion.Conexion;
import modelo.Producto;
import modelo.Persona;
import model.Vendedor;
import modelo.Cliente;
import modelo.DetalleVenta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910

public class TecnoStoreDAO {

    public Persona validarUsuario(String identificacion) {
<<<<<<< HEAD
        String sql = "SELECT * FROM personas WHERE identificacion = ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo_persona");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");

                if ("CLIENTE".equalsIgnoreCase(tipo)) {
                    return new Cliente(identificacion, nombre, correo, telefono);
                } else if ("VENDEDOR".equalsIgnoreCase(tipo)) {
                    return new Vendedor(identificacion, nombre, correo, telefono);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }
=======

        String sql = "SELECT * FROM personas WHERE identificacion = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String id = rs.getString("identificacion");
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo_persona");
                    String correo = rs.getString("correo");
                    String telefono = rs.getString("telefono");

                    if ("VENDEDOR".equalsIgnoreCase(tipo)) {

                        return new Vendedor(
                                id,
                                nombre,
                                correo,
                                telefono,
                                0.10
                        );

                    } else {

                        return new Cliente(
                                id,
                                nombre,
                                correo,
                                telefono
                        );
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error en BD: " + e.getMessage()
            );
        }

>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
        return null;
    }

    public List<Producto> obtenerProductos() {
<<<<<<< HEAD
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try (Connection conn = Conexion.getInstancia().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return lista;
    }

    public Producto buscarPorSku(String sku) {
        String sql = "SELECT * FROM celulares WHERE sku = ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sku);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por SKU: " + e.getMessage());
        }
        return null;
    }

    public List<Producto> filtrarPorMarca(String marca) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares WHERE marca LIKE ?";
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + marca + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("sku"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    rs.getString("gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar por marca: " + e.getMessage());
        }
        return lista;
    }

    public boolean confirmarCompra(Cliente cliente) {
        String sqlVenta = "INSERT INTO ventas (id_cliente, total) VALUES (?, ?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE celulares SET stock = stock - ? WHERE id = ?";

        Connection conn = null;
        try {
            conn = Conexion.getInstancia().getConexion();
            conn.setAutoCommit(false); // Transacción segura

            double total = cliente.calcularTotal();
            int idVentaGenerada;

            // 1. Insertar venta principal
            try (PreparedStatement stmtVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                stmtVenta.setString(1, cliente.getIdentificacion());
                stmtVenta.setDouble(2, total);
                stmtVenta.executeUpdate();

                ResultSet rsKeys = stmtVenta.getGeneratedKeys();
                if (rsKeys.next()) {
                    idVentaGenerada = rsKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la venta.");
                }
            }

            // 2. Insertar detalles y actualizar stock
            try (PreparedStatement stmtDetalle = conn.prepareStatement(sqlDetalle);
                 PreparedStatement stmtStock = conn.prepareStatement(sqlStock)) {
                
                for (DetalleCarrito item : cliente.getCarritoCompras()) {
                    stmtDetalle.setInt(1, idVentaGenerada);
                    stmtDetalle.setInt(2, item.getProducto().getId());
                    stmtDetalle.setInt(3, item.getCantidad());
                    stmtDetalle.setDouble(4, item.getSubtotal());
                    stmtDetalle.executeUpdate();

                    stmtStock.setInt(1, item.getCantidad());
                    stmtStock.setInt(2, item.getProducto().getId());
                    stmtStock.executeUpdate();
                }
            }

            conn.commit();
            
            // Generar reporte en TXT automáticamente
            ArchivoUtils.generarReporteTxt("Venta ID: " + idVentaGenerada + " | Cliente: " + cliente.getNombre() + " | Total: $" + total);
            
            cliente.limpiarCarrito();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.err.println("Error al confirmar la compra: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
=======

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (Connection con = Conexion.getInstancia().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                lista.add(
                        new Producto(
                                rs.getInt("id"),
                                rs.getString("sku"),
                                rs.getString("nombre"),
                                rs.getString("marca"),
                                rs.getString("categoria"),
                                rs.getDouble("precio"),
                                rs.getInt("stock"),
                                rs.getInt("stock_minimo")
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar productos: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    public List<Producto> filtrarPorMarca(String marca) {

        return obtenerProductos()
                .stream()
                .filter(p ->
                        p.getMarca().equalsIgnoreCase(marca)
                )
                .collect(Collectors.toList());
    }

    public Producto buscarPorSku(String sku) {

        String sql =
                "SELECT * FROM productos WHERE sku = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sku);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("sku"),
                            rs.getString("nombre"),
                            rs.getString("marca"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getInt("stock_minimo")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscando producto: "
                    + e.getMessage()
            );
        }

        return null;
    }

    public boolean confirmarCompra(
            Cliente cliente) {

        if (cliente.getCarritoCompras().isEmpty()) {

            System.out.println(
                    "El carrito está vacío."
            );

            return false;
        }

        Connection con = null;

        try {

            con = Conexion.getInstancia().getConexion();

            con.setAutoCommit(false);

            double total = cliente.calcularTotal();

            for (DetalleVenta detalle :
                    cliente.getCarritoCompras()) {

                String sqlStock =
                        "UPDATE productos " +
                        "SET stock = stock - ? " +
                        "WHERE sku = ? AND stock >= ?";

                try (PreparedStatement ps =
                             con.prepareStatement(sqlStock)) {

                    ps.setInt(
                            1,
                            detalle.getCantidad()
                    );

                    ps.setString(
                            2,
                            detalle.getProducto().getSku()
                    );

                    ps.setInt(
                            3,
                            detalle.getCantidad()
                    );

                    int filas =
                            ps.executeUpdate();

                    if (filas == 0) {

                        throw new SQLException(
                                "Stock insuficiente para: "
                                + detalle.getProducto().getNombre()
                        );
                    }
                }
            }

            String sqlVenta =
                    "INSERT INTO ventas " +
                    "(cliente_id, total) " +
                    "VALUES (?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlVenta)) {

                ps.setString(
                        1,
                        cliente.getIdentificacion()
                );

                ps.setDouble(
                        2,
                        total
                );

                ps.executeUpdate();
            }

            con.commit();

            cliente.limpiarCarrito();

            return true;

        } catch (SQLException e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException rollbackError) {

                System.out.println(
                        "Error realizando rollback: "
                        + rollbackError.getMessage()
                );
            }

            System.out.println(
                    "No se pudo completar la compra: "
                    + e.getMessage()
            );

            return false;

        } finally {

            try {

                if (con != null) {
                    con.setAutoCommit(true);
                }

            } catch (SQLException e) {

                System.out.println(
                        "Error restaurando conexión: "
                        + e.getMessage()
                );
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
            }
        }
    }

    public void exportarResumenVentasTxt() {
<<<<<<< HEAD
        String sql = "SELECT v.id, p.nombre, v.fecha, v.total FROM ventas v JOIN personas p ON v.id_cliente = p.identificacion";
        try (Connection conn = Conexion.getInstancia().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Venta ID: ").append(rs.getInt("id"))
                  .append(" | Cliente: ").append(rs.getString("nombre"))
                  .append(" | Fecha: ").append(rs.getTimestamp("fecha"))
                  .append(" | Total: $").append(rs.getDouble("total")).append("\n");
            }
            ArchivoUtils.generarReporteTxt(sb.toString());
        } catch (SQLException e) {
            System.err.println("Error al exportar resumen de ventas: " + e.getMessage());
=======

        String sql =
                "SELECT v.id, " +
                "p.nombre AS cliente, " +
                "v.total, " +
                "v.fecha " +
                "FROM ventas v " +
                "JOIN personas p " +
                "ON v.cliente_id = p.identificacion";

        try (Connection con =
                     Conexion.getInstancia().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);
             PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(
                                     "resumen_ventas.txt"
                             )
                     )) {

            writer.println(
                    "========================================"
            );

            writer.println(
                    "    REPORTE DE VENTAS TECNOSTORE"
            );

            writer.println(
                    "========================================"
            );

            double acumulado = 0;

            while (rs.next()) {

                writer.println(
                        "Venta ID: "
                        + rs.getInt("id")
                        + " | Cliente: "
                        + rs.getString("cliente")
                        + " | Total: $"
                        + rs.getDouble("total")
                        + " | Fecha: "
                        + rs.getTimestamp("fecha")
                );

                acumulado +=
                        rs.getDouble("total");
            }

            writer.println(
                    "----------------------------------------"
            );

            writer.println(
                    "RECAUDO TOTAL GENERAL: $"
                    + acumulado
            );

            System.out.println(
                    "Reporte 'resumen_ventas.txt' generado con éxito."
            );

        } catch (SQLException | IOException e) {

            System.out.println(
                    "Error al exportar reporte: "
                    + e.getMessage()
            );
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
        }
    }
}