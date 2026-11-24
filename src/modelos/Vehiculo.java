package modelos;
import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Vehiculo implements Serializable {
    private Integer id;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private Double precio;
    private Integer kilometraje;
    private Condicion condicion;
    private EstadoStock estadoStock;
    private static final long serialVersionUID = 1L;

    //#region Constructores
    public Vehiculo() {
    }

    public Vehiculo(Integer id, String marca, 
        String modelo, Integer anio, 
        String color, Double precio, 
        Integer kilometraje, Condicion condicion, 
        EstadoStock estadoStock) {

        setId(id);
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        setColor(color);
        setPrecio(precio);
        setKilometraje(kilometraje);
        setCondicion(condicion);
        setEstadoStock(estadoStock);

        validarKilometrajeYCondicion();
    }
    //#endregion

    //#region Getters
    public Integer getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public String getColor() {
        return color;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public EstadoStock getEstadoStock() {
        return estadoStock;
    }
    //#endregion

    //#region Setters
    public final void setId(Integer id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo");
        }
        this.id = id;
    }

    public final void setMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede ser nula o vacía");
        }
        this.marca = marca;
    }

    public final void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede ser nulo o vacío");
        }
        this.modelo = modelo;
    }

    public final void setAnio(Integer anio) {
        int anioActual = Year.now().getValue();

        if (anio == null || anio < 1885 || anio > anioActual) {
            throw new IllegalArgumentException(
                "El año de fabricación debe estar entre 1885 y el año actual"
            );
        }
        this.anio = anio;
    }

    public final void setColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("El color no puede ser nulo o vacío");
        }
        this.color = color;
    }

    public final void setPrecio(Double precio) {
        if (precio == null || !Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException(
                "El precio no puede ser nulo, infinito o negativo"
            );
        }
        this.precio = precio;
    }

    public final void setKilometraje(Integer kilometraje) {
        if (kilometraje == null || kilometraje < 0) {
            throw new IllegalArgumentException(
                "El kilometraje no puede ser nulo o negativo"
            );
        }
        this.kilometraje = kilometraje;

        validarKilometrajeYCondicion();
    }

    public final void setCondicion(Condicion condicion) {
        if (condicion == null) {
            throw new IllegalArgumentException(
                "La condicion del vehiculo no puede ser null"
            );
        }
        this.condicion = condicion;

        validarKilometrajeYCondicion();
    }

    public final void setEstadoStock(EstadoStock estadoStock) {
        if (estadoStock == null) {
            throw new IllegalArgumentException(
                "El estado de stock no puede ser nulo"
            );
        }
        this.estadoStock = estadoStock;
    }
    //#endregion

    //#region Validaciones
    private void validarKilometrajeYCondicion() {
        if (condicion == null || kilometraje == null) {
            return; // Si alguno todavía no se cargó, no valido nada y salgo
        }

        if (condicion == Condicion.NUEVO && kilometraje > 0) {
            throw new IllegalArgumentException(
                "El kilometraje de un vehículo nuevo debe ser 0"
            );
        }
    }
    //#endregion

    @Override 
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Vehiculo otro = (Vehiculo) obj;
        return id != null && Objects.equals(id, otro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", kilometraje=" + kilometraje +
                ", condicion=" + condicion +
                ", estado_stock=" + estadoStock
                ;
    }
}

