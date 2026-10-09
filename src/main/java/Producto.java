public class Producto {

    private final int id;
    private final String sku;
    private final String nombre;
    private final String marca;
    private final String categoria;
    private final double precio;
    private int stock;
    private final int stockMinimo;

    public Producto(
            int id,
            String sku,
            String nombre,
            String marca,
            String categoria,
            double precio,
            int stock,
            int stockMinimo) {

        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public int getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {

        return "SKU: " + sku
                + " | Producto: " + nombre
                + " | Marca: " + marca
                + " | Categoría: " + categoria
                + " | Precio: $" + precio
                + " | Stock: " + stock
                + " | Stock mínimo: " + stockMinimo;
    }
}