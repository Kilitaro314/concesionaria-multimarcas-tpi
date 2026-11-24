package excepcion;

/** Error para búsquedas o eliminaciones sin coincidencias. */
public class ExcepcionNoEncontrado extends ExcepcionNegocio {
    /** Crea el error con el identificador que no se encontró. */
    public ExcepcionNoEncontrado(String message) { super(message); }
}

