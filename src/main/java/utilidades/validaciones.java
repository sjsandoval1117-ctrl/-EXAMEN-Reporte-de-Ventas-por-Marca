package utilidades;

public class validaciones {
    public static boolean esNumero(String texto) {
        try {
            Double.valueOf(texto);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}