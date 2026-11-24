package modelos;
public class Motocicleta extends Vehiculo {
    
    private Integer cilindrada;
    private TipoMoto tipoMoto;
    private static final long serialVersionUID = 1L;

    public Motocicleta(){
        super();
    }

    public Motocicleta(Integer id, String marca, 
        String modelo, Integer anio, 
        String color, Double precio, 
        Integer kilometraje, Condicion condicion, 
        EstadoStock estadoStock, Integer cilindrada, TipoMoto tipoMoto) {

        super(id, marca, modelo, anio, color, precio, kilometraje, condicion, estadoStock);
        
        setCilindrada(cilindrada);
        setTipoMoto(tipoMoto);
    }

    //#region Getters
    public Integer getCilindrada() {
        return cilindrada;
    }

    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }
    //#endregion

    //#region Setters
    public final void setCilindrada(Integer cilindrada) {
        if (cilindrada == null || cilindrada <= 0){
            throw new IllegalArgumentException("La cilindrada debe ser un valor positivo");
        }
        this.cilindrada = cilindrada;
    }

    public final void setTipoMoto(TipoMoto tipoMoto) {
        if (tipoMoto == null){
            throw new IllegalArgumentException("El tipo de moto no puede ser null");
        }
        this.tipoMoto = tipoMoto;
    }
    //#endregion

    @Override
    public String toString() {
        return "Motocicleta{" +
                super.toString() +
                ", cilindrada=" + cilindrada +
                ", tipoMoto=" + tipoMoto +
                '}';
    }

}