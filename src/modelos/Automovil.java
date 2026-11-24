package modelos;

public class Automovil extends Vehiculo {
    
    private static final long serialVersionUID = 1L;
    private TipoCarroceria tipoCarroceria;


    /** Crea una instancia de Automovil. */
    public Automovil(){
        super();
    }

    /** Crea una instancia de Automovil. */
    public Automovil(Integer id, String marca, 
        String modelo, Integer anio, 
        String color, Double precio, 
        Integer kilometraje, Condicion condicion, 
        EstadoStock estadoStock, TipoCarroceria tipoCarroceria) {

        super(id, marca, modelo, anio, color, precio, kilometraje, condicion, estadoStock);
        setTipoCarroceria(tipoCarroceria);
    }

    /** Devuelve tipo de carrocería. */
    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    /** Establece el tipo de carrocería. */
    public final void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        if (tipoCarroceria == null){
            throw new IllegalArgumentException("El tipo de carroceria no puede ser null");
        }
        this.tipoCarroceria = tipoCarroceria;
    }

    /** Devuelve una descripción textual de esta instancia. */
    @Override
    public String toString() {
        return "Automovil{" +
                super.toString() +
                ", tipoCarroceria=" + tipoCarroceria +
                '}';
    }

}
