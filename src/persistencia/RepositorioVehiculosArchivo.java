package persistencia;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import modelos.Vehiculo;

/** Repositorio de vehículos que persiste en inventario.dat.
 */
public class RepositorioVehiculosArchivo implements Repositorio<Vehiculo> {

    private static final Path ARCHIVO = Path.of("inventario.dat");

    /** Devuelve los vehículos guardados.
     * @return lista de vehículos
     */
    @Override
    public List<Vehiculo> listar() {
        if (!Files.exists(ARCHIVO)) return new ArrayList<>();
        try (InputStream in = Files.newInputStream(ARCHIVO);
            ObjectInputStream lector = new ObjectInputStream(in)){
                Object objecto = lector.readObject();
                @SuppressWarnings("unchecked")
                List<Vehiculo> datos = (List<Vehiculo>) objecto;
                return new ArrayList<>(datos);
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("No se pudo interpretar el inventario", e);
        }
    }
    
    /** Guarda la lista recibida en inventario.dat, reemplazando el contenido anterior.
     * @param datos lista de vehículos a guardar
     */
    @Override
    public void guardar(List<Vehiculo> datos) {
        try (OutputStream tubo = Files.newOutputStream(ARCHIVO);
            ObjectOutputStream escritor = new ObjectOutputStream(tubo)) {
            escritor.writeObject(datos);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}