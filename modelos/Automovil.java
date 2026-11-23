public class Automovil extends Vehiculo {
    private TipoCarroceria tipoCarroceria;

    public Automovil(){
        super();
    }

    public Automovil(Integer id, String marca, 
        String modelo, Integer anio, 
        String color, Double precio, 
        Integer kilometraje, Condicion condicion, 
        EstadoStock estadoStock, TipoCarroceria tipoCarroceria) {

        super(id, marca, modelo, anio, color, precio, kilometraje, condicion, estadoStock);
        setTipoCarroceria(tipoCarroceria);
    }

    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    public final void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        if (tipoCarroceria == null){
            throw new IllegalArgumentException("El tipo de carroceria no puede ser null");
        }
        this.tipoCarroceria = tipoCarroceria;
    }

    @Override
    public String toString() {
        return "Automovil{" +
                super.toString() +
                ", tipoCarroceria=" + tipoCarroceria +
                '}';
    }

}