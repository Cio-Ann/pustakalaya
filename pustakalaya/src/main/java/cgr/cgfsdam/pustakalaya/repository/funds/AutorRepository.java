package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;

/**
 * Interfaz del repositorio de autores.
 *
 * @author CGR-Casa
 */
@Repository("autorRepository")
public interface AutorRepository extends JpaRepository<Autor, Long> {
	/**
	 * Busca autores por su nombre exacto
	 * 
	 * @param nombre String nombre de autor a buscar
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreAllIgnoreCase(String nombre);
	
	/**
	 * Busca autores cuyo nombre contenga la cadena de busqueda
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
	 * Busca autores cuyos nombre y apellidos contengan las cadenas dadas
	 * 
	 * @param nombre String texto a buscar en el nombre
	 * @param apellidos String texto a buscar en los apellidos
	 * @return List<Autor> autores coincidentes
	 */
	List<Autor> findByNombreContainingAndApellidosContainingAllIgnoreCase(String nombre, String apellidos);
	
	/**
	 * Cuenta el número de recursos de los que el autor dado es autor o coautor.
	 * 
	 * @param autor Autor a contar el número de obras.
	 * @return Long número de obras de las que el autor recibido es participe.
	 */
	@Query("SELECT count(r) FROM Recurso AS r WHERE :autor MEMBER OF r.autores")
	Long countResourcesByAutor(@Param("autor")Autor autor);
	

}
