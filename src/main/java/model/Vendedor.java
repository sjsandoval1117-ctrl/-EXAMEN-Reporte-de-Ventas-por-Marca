package model;

import modelo.Persona;

public class Vendedor extends Persona {
    private double comision;

    public Vendedor(String identificacion, String nombre, String correo, String telefono, double comision) {
        super(identificacion, nombre, correo, telefono);
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol en el Sistema: Vendedor (Acceso a gestión de inventario y reportes de ventas).");
    }
}