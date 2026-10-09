package modelo;

public class Celular {
    private int id;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private String gama;
    private double precio;
    private int stock;
    private int stockMinimo;

    public Celular(int id, String marca, String modelo, String sistemaOperativo, String gama, double precio, int stock, int stockMinimo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public String getGama() { return gama; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMinimo() { return stockMinimo; }
}