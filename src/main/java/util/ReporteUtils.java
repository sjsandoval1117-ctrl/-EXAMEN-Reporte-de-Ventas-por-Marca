package utilidades;

import modelo.Celular;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteUtils {

    // Filtrar celulares con stock bajo (< 5 unidades o menor al stock mínimo)
    public static List<Celular> filtrarStockBajo(List<Celular> celulares) {
        return celulares.stream()
                .filter(c -> c.getStock() < 5)
                .collect(Collectors.toList());
    }

    // Filtrar por marca usando Stream API
    public static List<Celular> filtrarPorMarca(List<Celular> celulares, String marca) {
        return celulares.stream()
                .filter(c -> c.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
    }

    // Filtrar por gama usando Stream API
    public static List<Celular> filtrarPorGama(List<Celular> celulares, String gama) {
        return celulares.stream()
                .filter(c -> c.getGama().equalsIgnoreCase(gama))
                .collect(Collectors.toList());
    }
}
