package cgr.cgfsdam.pustakalaya.repository.funds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Idioma;

/**
 * Interfaz del repositorio de la entidad Idioma.
 *
 * @author CGR-Casa
 */
@Repository("idiomaRepository")
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

	/**
	 * Busca un idioma a partir de su nombre exacto.
	 * 
	 * @param nombre
	 *            String Nombre a buscar
	 * @return Idioma entidad encontrada
	 */
	Idioma findByNombreIgnoreCase(String nombre);

	/**
	 * Devuelve el número de recursos relacionados con el idioma dado.
	 * 
	 * @param idioma
	 *            Idioma entidad con al que buscar
	 * @return Long número de recursos relacionados
	 */
	@Query("SELECT count(r) FROM Recurso AS r WHERE r.idioma = :idioma")
	Long countResourcesByIdioma(@Param("idioma") Idioma idioma);
}
