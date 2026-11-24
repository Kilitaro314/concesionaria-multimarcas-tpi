package persistencia;

import java.util.List;

/** Almacén genérico de objetos.
 * @param <T> el tipo de objeto que se gestionará en el repositorio
 */
public interface Repositorio<T> {
    /** Devuelve todas las entidades almacenadas.
     * @return lista de entidades
     */
    List<T> listar();

    /** Guarda la lista recibida, reemplazando las entidades almacenadas.
     * @param datos lista de entidades a guardar
     */
    void guardar(List<T> datos);
}