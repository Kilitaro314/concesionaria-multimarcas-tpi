package aplicacion;

import java.util.Scanner;

/** Centraliza la lectura y validación básica de datos de consola. */
public class EntradaConsola {
    private final Scanner scanner;

    /** Inicializa el lector compartido del menú. */
    public EntradaConsola(Scanner scanner) { this.scanner = scanner; }

    /** Lee texto obligatorio y repite si está vacío. */
    public String leerObligatorio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("El dato es obligatorio.");
        }
    }

    /** Lee un entero y repite ante formatos inválidos. */
    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Ingresá un número entero válido."); }
        }
    }

    /** Lee un importe y admite coma o punto decimal. */
    public Double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Double.valueOf(scanner.nextLine().trim().replace(',', '.')); }
            catch (NumberFormatException ex) { System.out.println("Ingresá un importe numérico válido."); }
        }
    }

    /** Lee una opción dentro de un rango inclusivo. */
    public int leerOpcion(String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo && valor <= maximo) return valor;
            System.out.printf("Elegí una opción entre %d y %d.%n", minimo, maximo);
        }
    }

    /** Presenta los valores de un enum y devuelve el elegido. */
    public <E extends Enum<E>> E leerEnum(String mensaje, Class<E> tipo) {
        E[] opciones = tipo.getEnumConstants();
        for (int i = 0; i < opciones.length; i++) System.out.printf("%d. %s%n", i + 1, opciones[i]);
        return opciones[leerOpcion(mensaje, 1, opciones.length) - 1];
    }
}
