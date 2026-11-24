package excepcion;

/** Error que describe datos de entrada inválidos. */
public class ExcepcionValidacion extends ExcepcionNegocio {
    /** Crea el error con el detalle de validación. */
    public ExcepcionValidacion(String message) { super(message); }
}

