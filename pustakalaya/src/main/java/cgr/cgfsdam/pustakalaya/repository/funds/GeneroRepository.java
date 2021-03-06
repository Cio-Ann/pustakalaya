package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Genero;

@Repository("generoRepository")
public interface GeneroRepository extends JpaRepository<Genero, Long> {

	/**
	 * Busca un genero por su nombre exacto.
	 * 
	 * @param nombre
	 *            String nombre del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreIgnoreCase(String nombre);

	/**
	 * Busca un genero por su nombre y descripción
	 * 
	 * @param nombre
	 *            String nombre del genero a buscar
	 * @param descripcion
	 *            String descripción del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreAndDescripcionAllIgnoreCase(String nombre, String descripcion);

	/**
	 * Cuenta el número de recursos relacionados con el género.
	 * 
	 * Construye la consulta a traves de una sentencia JPQL en vez de a través de naming convention
	 * 
	 * @param genero
	 *            Genero por el que buscar
	 * @return Long número de recursos relacionados con el género dado
	 */
	@Query("SELECT count(r) FROM Recurso AS r WHERE :genero MEMBER OF r.generos")
	Long countResourcesByGenero(@Param("genero") Genero genero);

}
