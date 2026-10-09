package modelo;

public class Producto {
    private int id;
    private String sku;
<<<<<<< HEAD
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private String gama;
=======
    private String nombre;
    private String marca;
    private String categoria;
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
    private double precio;
    private int stock;
    private int stockMinimo;

<<<<<<< HEAD
    public Producto(int id, String sku, String marca, String modelo, String sistemaOperativo, String gama, double precio, int stock, int stockMinimo) {
        this.id = id;
        this.sku = sku;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
=======
    public Producto(int id, String sku, String nombre, String marca, String categoria, double precio, int stock, int stockMinimo) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

<<<<<<< HEAD
    // Método requerido por el carrito y la vista
    public String getNombre() {
        return marca + " " + modelo;
    }

    public int getId() { return id; }
    public String getSku() { return sku; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public String getGama() { return gama; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMinimo() { return stockMinimo; }

    @Override
    public String toString() {
        return "ID: " + id + " | SKU: " + sku + " | " + getNombre() + " (" + gama + ") | Precio: $" + precio + " | Stock: " + stock;
=======
    public int getId() { return id; }
    public String getSku() { return sku; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }
    
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "SKU: " + sku + " | " + nombre + " (" + marca + ") - Precio: $" + precio + " [Stock: " + stock + " / Min: " + stockMinimo + "]";
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
    }
}