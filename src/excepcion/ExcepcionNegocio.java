package excepcion;

/** Error controlado que informa una regla del negocio incumplida. */
public class ExcepcionNegocio extends Exception {
    /** Crea el error con un mensaje apto para mostrar al usuario. */
    public ExcepcionNegocio(String message) { super(message); }
}

