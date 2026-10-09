package model;

import java.util.List;

public class Telefono {
    private String marca;
    private double precio;
    private categoria categoria;
    private List<String> funcionalidades;

    public Telefono(String marca, double precio, String nombreCategoria, List<String> funcionalidades) {
        this.marca = marca;
        this.precio = precio;
        this.categoria = new categoria(nombreCategoria);
        this.funcionalidades = funcionalidades;
    }

    public Telefono(String marca, double precio, categoria categoria, List<String> funcionalidades) {
        this.marca = marca;
        this.precio = precio;
        this.categoria = categoria;
        this.funcionalidades = funcionalidades;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public List<String> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<String> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    @Override
    public String toString() {
        return String.format("Telefono [Marca: %s, Precio: $%.2f, Categoria: %s, Funcionalidades: %s]",
                marca, precio, categoria, funcionalidades);
    }
}