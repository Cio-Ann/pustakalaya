package cgr.cgfsdam.pustakalaya.service.funds;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;

/**
 * Interfaz del servicio de la entidad Autor
 *
 * @author CGR-Casa
 */
public interface AutorService {
	/**
	 * Busca autores por su nombre exacto.
	 * 
	 * @param nombre String nombre de autor a buscar
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreAllIgnoreCase(String nombre);
	
	/**
	 * Busca autores cuyo nombre contenga la cadena de busqueda.
	 * 
	 * @param nombre String texto a buscar en el nombre
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreContainingAllIgnoreCase(String nombre);
	
	/**
	 * Busca autores por sus apellidos exactos.
	 * 
	 * @param apellidos String apellidos a buscar
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByApellidosAllIgnoreCase(String apellidos);
	
	/**
	 * Busca autores cuyos apellidos contengan el texto dado.
	 * 
	 * @param apellidos String cadena a buscar en los apellidos
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByApellidosContainingAllIgnoreCase(String apellidos);
	
	/**
	 * Busca autores cuyos nombre y apellidos coincidan con las cadenas dadas.
	 * 
	 * @param nombre String texto a buscar en el nombre
	 * @param apellidos String texto a buscar en los apellidos
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreAndApellidosAllIgnoreCase(String nombre, String apellidos);

	/**
	 * Busca autores cuyos nombre y apellidos contengan las cadenas dadas.
	 * 
	 * @param nombre String texto a buscar en el nombre
	 * @param apellidos String texto a buscar en los apellidos
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreContainingAndApellidosContainingAllIgnoreCase(String nombre, String apellidos);
	
	/**
	 * Guarda un autor en base de datos.
	 * 
	 * @param autor Autor entidad a guardar
	 * @return Autor entidad guardada.
	 */
	Autor save(Autor autor);

	/**
	 * Recupera toda la lista de autores de bbdd
	 * @return
	 */
	List<Autor>  findAll();

	/**
	 * Recupera un autor por su id.
	 * 
	 * @param idAutor Long id del autor a recuperar
	 * @return Autor entidad coincidente o null
	 */
	Autor findById(Long idAutor);

	/**
	 * Elimina el autor recibido de base de datos.
	 * 
	 * @param autor
	 */
	void delete(Autor autor);

	/**
	 * Cuenta el número de recursos de los que la entidad dada es autor o coautor.
	 * 
	 * @param autor Autor entidad a ser
	 * @return Long número de obras de las que el autor dado es participe
	 */
	Long countResourcesByAutor(Autor autor);
}
