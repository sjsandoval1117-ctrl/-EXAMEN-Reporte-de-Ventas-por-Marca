package utilidades;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArchivoUtils {

    public static int leerEnteroValidado(Scanner scanner, String mensaje) {
        int numero = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.next();
            }
        }
        scanner.nextLine(); 
        return numero;
    }

    public static void guardarTexto(String nombreArchivo, String contenido) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.print(contenido);
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}