package utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoUtils {

    public static void generarReporteTxt(String contenidoResumen) {
        // Uso obligatorio de try-with-resources para excepciones y cierre automático
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_ventas.txt", true))) {
            writer.write("=== REPORTE DE VENTAS TECNOSTORE ===\n");
            writer.write(contenidoResumen);
            writer.write("\n----------------------------------------\n");
            System.out.println("Archivo 'reporte_ventas.txt' generado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar el archivo de reporte: " + e.getMessage());
        }
    }
}