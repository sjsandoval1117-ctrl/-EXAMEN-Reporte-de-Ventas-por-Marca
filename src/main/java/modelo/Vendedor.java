package modelo;

public class Vendedor extends Persona {
    public Vendedor(String identificacion, String nombre, String correo, String telefono) {
        super(identificacion, nombre, correo, telefono);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol en el sistema: VENDEDOR");
    }
}