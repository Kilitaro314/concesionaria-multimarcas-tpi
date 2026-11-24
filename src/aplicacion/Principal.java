package aplicacion;

import excepcion.ExcepcionNegocio;
import modelos.Automovil;
import modelos.Camioneta;
import modelos.Condicion;
import modelos.EstadoStock;
import modelos.Motocicleta;
import modelos.TipoCabina;
import modelos.TipoCarroceria;
import modelos.TipoMoto;
import modelos.TipoTraccion;
import modelos.Vehiculo;
import persistencia.RepositorioVehiculosArchivo;
import servicio.ServicioVehiculos;
import java.io.UncheckedIOException;
import java.util.Scanner;

/** Inicia el sistema de inventario por consola. */
public class Principal {
    private final EntradaConsola entrada = new EntradaConsola(new Scanner(System.in));
    private final ServicioVehiculos servicio = new ServicioVehiculos(new RepositorioVehiculosArchivo());

    /** Ejecuta el menú principal hasta elegir salir. */
    public static void main(String[] args) {
        try { new Principal().ejecutar(); }
        catch (UncheckedIOException | IllegalStateException ex) {
            System.out.println("No se pudo cargar el archivo de inventario: " + ex.getMessage());
        }
    }

    /** Despacha las opciones del menú. */
    private void ejecutar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== Concesionaria Multimarcas ===");
            System.out.println("1. Registrar unidad\n2. Listar inventario\n3. Buscar por ID\n4. Eliminar unidad\n0. Salir");
            int opcion = entrada.leerOpcion("Opción: ", 0, 4);
            try {
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> eliminar();
                    case 0 -> { continuar = false; System.out.println("Hasta luego."); }
                    default -> { }
                }
            } catch (ExcepcionNegocio ex) {
                System.out.println("No se pudo completar la operación: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Datos inválidos: " + ex.getMessage());
            } catch (UncheckedIOException ex) {
                System.out.println("No se pudo guardar el inventario: " + ex.getMessage());
            }
        }
    }

    /** Solicita datos y registra un vehículo del tipo elegido. */
    private void registrar() throws ExcepcionNegocio {
        System.out.println("\nTipo: 1. Automóvil  2. Camioneta  3. Motocicleta");
        int tipo = entrada.leerOpcion("Tipo: ", 1, 3);
        int id = entrada.leerEntero("ID numérico: ");
        String marca = entrada.leerObligatorio("Marca: ");
        String modelo = entrada.leerObligatorio("Modelo: ");
        int anio = entrada.leerEntero("Año: ");
        String color = entrada.leerObligatorio("Color: ");
        Double precio = entrada.leerDecimal("Precio de venta: $");
        Condicion condicion = entrada.leerEnum("Condición: ", Condicion.class);
        int kilometraje = condicion == Condicion.NUEVO ? 0 : entrada.leerEntero("Kilometraje: ");
        EstadoStock estado = entrada.leerEnum("Estado de stock: ", EstadoStock.class);

        Vehiculo vehiculo;
        if (tipo == 1) {
            TipoCarroceria carroceria = entrada.leerEnum("Tipo de carrocería: ", TipoCarroceria.class);
            vehiculo = new Automovil(id, marca, modelo, anio, color, precio, kilometraje, condicion, estado, carroceria);
        } else if (tipo == 2) {
            int capacidad = entrada.leerEntero("Capacidad de carga: ");
            TipoCabina cabina = entrada.leerEnum("Tipo de cabina: ", TipoCabina.class);
            TipoTraccion traccion = entrada.leerEnum("Tipo de tracción: ", TipoTraccion.class);
            vehiculo = new Camioneta(id, marca, modelo, anio, color, precio, kilometraje, condicion, estado, capacidad, cabina, traccion);
        } else {
            int cilindrada = entrada.leerEntero("Cilindrada: ");
            TipoMoto tipoMoto = entrada.leerEnum("Tipo de motocicleta: ", TipoMoto.class);
            vehiculo = new Motocicleta(id, marca, modelo, anio, color, precio, kilometraje, condicion, estado, cilindrada, tipoMoto);
        }
        servicio.registrar(vehiculo);
        System.out.println("Unidad registrada: " + vehiculo);
    }

    /** Muestra todas las unidades registradas. */
    private void listar() {
        var vehiculos = servicio.listar();
        if (vehiculos.isEmpty()) { System.out.println("El inventario está vacío."); return; }
        vehiculos.forEach(System.out::println);
    }

    /** Busca y muestra una unidad por su ID. */
    private void buscar() throws ExcepcionNegocio {
        System.out.println(servicio.buscarPorId(entrada.leerEntero("ID a buscar: ")));
    }

    /** Elimina una unidad identificada por su ID. */
    private void eliminar() throws ExcepcionNegocio {
        servicio.eliminar(entrada.leerEntero("ID a eliminar: "));
        System.out.println("Unidad eliminada.");
    }
}

