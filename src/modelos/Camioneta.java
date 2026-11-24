package modelos;
public class Camioneta extends Vehiculo {
    
    private Integer capacidadCarga;
    private TipoCabina tipoCabina;
    private TipoTraccion traccion;
    private static final long serialVersionUID = 1L;

    /** Crea una instancia de Camioneta. */
    public Camioneta(){
        super();
    }

    /** Crea una instancia de Camioneta. */
    public Camioneta(Integer id, String marca, 
        String modelo, Integer anio, 
        String color, Double precio, 
        Integer kilometraje, Condicion condicion, 
        EstadoStock estadoStock, Integer capacidadCarga, 
        TipoCabina tipoCabina, TipoTraccion traccion) {

        super(id, marca, modelo, anio, color, precio, kilometraje, condicion, estadoStock);
        
        setCapacidadCarga(capacidadCarga);
        setTipoCabina(tipoCabina);
        setTraccion(traccion);
    }
    
    //#region Getters
    /** Devuelve capacidad de carga. */
    public Integer getCapacidadCarga() {
        return capacidadCarga;
    }

    /** Devuelve tipo de cabina. */
    public TipoCabina getTipoCabina() {
        return tipoCabina;
    }

    /** Devuelve tracción. */
    public TipoTraccion getTraccion() {
        return traccion;
    }
    //#endregion

    //#region Setters
    /** Establece la capacidad de carga. */
    public final void setCapacidadCarga(Integer capacidadCarga) {
        if (capacidadCarga == null || capacidadCarga <= 0){
            throw new IllegalArgumentException("La capacidad de carga debe ser un valor positivo");
        }
        this.capacidadCarga = capacidadCarga;
    }

    /** Establece el tipo de cabina. */
    public final void setTipoCabina(TipoCabina tipoCabina) {
        if (tipoCabina == null){
            throw new IllegalArgumentException("El tipo de cabina no puede ser nulo");
        }
        this.tipoCabina = tipoCabina;
    }

    /** Establece la tracción. */
    public final void setTraccion(TipoTraccion traccion) {
        if (traccion == null){
            throw new IllegalArgumentException("La traccion no puede ser nula");
        }
        this.traccion = traccion;
    }
    //#endregion

    /** Devuelve una descripción textual de esta instancia. */
    @Override
    public String toString() {
        return "Camioneta{" +
                super.toString() +
                ", capacidadCarga=" + capacidadCarga +
                ", tipoCabina=" + tipoCabina +
                ", traccion=" + traccion +
                '}';
    }
}
