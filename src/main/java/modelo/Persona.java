package modelo;

public abstract class Persona {
<<<<<<< HEAD
    protected String identificacion;
    protected String nombre;
    protected String correo;
    protected String telefono;
=======
    private String identificacion;
    private String nombre;
    private String correo;
    private String telefono;
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910

    public Persona(String identificacion, String nombre, String correo, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

<<<<<<< HEAD
    public abstract void mostrarRol();

    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
=======
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public abstract void mostrarRol();
>>>>>>> 9f9df755ed2b7b638f8435ac2bbe9bb4cf9fd910
}