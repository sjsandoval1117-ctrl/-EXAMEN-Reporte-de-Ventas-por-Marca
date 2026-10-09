package util;

import java.util.Scanner;

public class validaciones {

    private final Scanner scanner;

    public validaciones() {
        scanner = new Scanner(System.in);
    }

    public String texto(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String dato = scanner.nextLine().trim();

            if (!dato.isEmpty()) {
                return dato;
            }

            System.out.println("El campo no puede estar vacío.");
        }
    }

    public int entero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Debe ingresar un número entero."
                );
            }
        }
    }

    public double decimal(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Debe ingresar un número válido."
                );
            }
        }
    }

    public int menu(
            String mensaje,
            int minimo,
            int maximo) {

        while (true) {

            int opcion = entero(mensaje);

            if (opcion >= minimo && opcion <= maximo) {
                return opcion;
            }

            System.out.println(
                    "La opción debe estar entre "
                    + minimo
                    + " y "
                    + maximo
                    + "."
            );
        }
    }

    public int stock(
            String mensaje,
            int minimo,
            int maximo) {

        while (true) {

            int cantidad = entero(mensaje);

            if (cantidad < minimo) {

                System.out.println(
                        "El stock no puede ser menor que "
                        + minimo
                        + "."
                );

            } else if (cantidad > maximo) {

                System.out.println(
                        "El stock no puede ser mayor que "
                        + maximo
                        + "."
                );

            } else {

                return cantidad;
            }
        }
    }

    public int stockMinimo(
            String mensaje,
            int stock,
            int minimo,
            int maximo) {

        while (true) {

            int cantidad = entero(mensaje);

            if (cantidad < minimo) {

                System.out.println(
                        "El stock mínimo no puede ser menor que "
                        + minimo
                        + "."
                );

            } else if (cantidad > maximo) {

                System.out.println(
                        "El stock mínimo no puede ser mayor que "
                        + maximo
                        + "."
                );

            } else if (cantidad > stock) {

                System.out.println(
                        "El stock mínimo no puede ser mayor "
                        + "que el stock disponible."
                );

            } else {

                return cantidad;
            }
        }
    }
}
