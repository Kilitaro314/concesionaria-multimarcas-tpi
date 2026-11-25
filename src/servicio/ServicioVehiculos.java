package servicio;

import excepcion.ExcepcionNoEncontrado;
import excepcion.ExcepcionValidacion;
import modelos.Automovil;
import modelos.Camioneta;
import modelos.Condicion;
import modelos.Motocicleta;
import modelos.Vehiculo;
import persistencia.Repositorio;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Aplica las reglas de negocio del inventario. */
public class ServicioVehiculos {
    private final Repositorio<Vehiculo> repositorio;

    /** Crea el servicio con su repositorio. */
    public ServicioVehiculos(Repositorio<Vehiculo> repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio, "El repositorio es obligatorio.");
    }

    /** Valida y registra una unidad. */
    public void registrar(Vehiculo vehiculo) throws ExcepcionValidacion {
        validar(vehiculo);
        List<Vehiculo> inventario = new ArrayList<>(repositorio.listar());
        if (inventario.stream().anyMatch(actual -> actual.getId().equals(vehiculo.getId()))) {
            throw new ExcepcionValidacion("Ya existe una unidad con ese ID.");
        }
        inventario.add(vehiculo);
        repositorio.guardar(inventario);
    }

    /** Devuelve todas las unidades registradas. */
    public List<Vehiculo> listar() { return repositorio.listar(); }

    /** Busca una unidad por ID o informa que no existe. */
    public Vehiculo buscarPorId(Integer id) throws ExcepcionNoEncontrado {
        return repositorio.listar().stream()
                .filter(vehiculo -> vehiculo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ExcepcionNoEncontrado("No existe una unidad con ese ID."));
    }

    /** Elimina una unidad existente por ID. */
    public void eliminar(Integer id) throws ExcepcionNoEncontrado {
        List<Vehiculo> inventario = new ArrayList<>(repositorio.listar());
        boolean eliminado = inventario.removeIf(vehiculo -> vehiculo.getId().equals(id));
        if (!eliminado) throw new ExcepcionNoEncontrado("No existe una unidad con ese ID.");
        repositorio.guardar(inventario);
    }

    /** Comprueba los datos requeridos y las reglas del dominio. */
    private void validar(Vehiculo vehiculo) throws ExcepcionValidacion {
        if (vehiculo == null) throw new ExcepcionValidacion("La unidad es obligatoria.");
        if (vehiculo.getId() == null || vehiculo.getId() <= 0) throw new ExcepcionValidacion("El ID debe ser positivo.");
        requerido(vehiculo.getMarca(), "Marca");
        requerido(vehiculo.getModelo(), "Modelo");
        requerido(vehiculo.getColor(), "Color");
        if (vehiculo.getAnio() == null || vehiculo.getAnio() < 1885 || vehiculo.getAnio() > Year.now().getValue()) {
            throw new ExcepcionValidacion("El año debe estar entre 1885 y el año actual.");
        }
        if (vehiculo.getPrecio() == null || !Double.isFinite(vehiculo.getPrecio()) || vehiculo.getPrecio() < 0) {
            throw new ExcepcionValidacion("El precio debe ser válido e igual o mayor que cero.");
        }
        if (vehiculo.getKilometraje() == null || vehiculo.getKilometraje() < 0) {
            throw new ExcepcionValidacion("El kilometraje no puede ser negativo.");
        }
        if (vehiculo.getCondicion() == null || vehiculo.getEstadoStock() == null) {
            throw new ExcepcionValidacion("La condición y el estado de stock son obligatorios.");
        }
        if (vehiculo.getCondicion() == Condicion.NUEVO && vehiculo.getKilometraje() != 0) {
            throw new ExcepcionValidacion("Una unidad nueva debe tener 0 km.");
        }
        if (vehiculo instanceof Automovil auto && auto.getTipoCarroceria() == null) {
            throw new ExcepcionValidacion("El tipo de carrocería es obligatorio.");
        }
        if (vehiculo instanceof Camioneta camioneta && (camioneta.getCapacidadCarga() == null || camioneta.getCapacidadCarga() <= 0 || camioneta.getTipoCabina() == null || camioneta.getTraccion() == null)) {
            throw new ExcepcionValidacion("La capacidad, cabina y tracción de la camioneta son obligatorias.");
        }
        if (vehiculo instanceof Motocicleta moto && (moto.getCilindrada() == null || moto.getCilindrada() <= 0 || moto.getTipoMoto() == null)) {
            throw new ExcepcionValidacion("La cilindrada y el tipo de motocicleta son obligatorios.");
        }
    }

    /** Rechaza textos vacíos indicando el dato faltante. */
    private void requerido(String valor, String nombre) throws ExcepcionValidacion {
        if (valor == null || valor.isBlank()) throw new ExcepcionValidacion(nombre + " es obligatorio.");
    }
}
