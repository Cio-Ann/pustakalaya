package cgr.cgfsdam.pustakalaya.service.funds;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Genero;

/**
 * Interfaz del servicio de la entidad Genero
 *
 * @author CGR-Casa
 */
public interface GeneroService {

	/**
	 * Busca un genero por su nombre exacto.
	 * 
	 * @param nombre String nombre del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreIgnoreCase(String nombre);

	/**
	 * Busca un genero por su nombre y descripción
	 * 
	 * @param nombre String nombre del genero a buscar
	 * @param descripcion String descripción del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreAndDescripcionAllIgnoreCase(String nombre, String descripcion);

	/**
	 * Guarda el genero literario en base de datos.
	 * 
	 * @param genero Genero objeto a guardar
	 */
	void save(Genero genero);

	/**
	 * Recupera todos los generos de la base de datos.
	 * 
	 * @return List<Genero> generos persistidos
	 */
	List<Genero> findAll();

	/**
	 * Recupera un genero por su id.
	 * 
	 * @param idGenero Long id del genero
	 * @return Genero entidad coincidente o null
	 */
	Genero findById(Long idGenero);

	/**
	 * Elimina un genero de base de datos.
	 * 
	 * @param genero Genero el genero a eliminar
	 */
	void delete(Genero genero);

	/**
	 * Cuenta el número de recursos seleccionados con el género dado.
	 * 
	 * @param genero Genero entidad por la que buscar
	 * @return Long número de recursos relacionados con el género
	 */
	Long countResourcesByGenero(Genero genero);

}
