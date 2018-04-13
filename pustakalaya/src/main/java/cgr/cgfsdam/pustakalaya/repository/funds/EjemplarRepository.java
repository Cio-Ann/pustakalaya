package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;

/**
 * Interfaz del repositorio de la entidad Ejemplar
 *
 * @author CGR-Casa
 */
@Repository("ejemplarRepository")
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

	/**
	 * Encuentra un ejemplar por su código.
	 * 
	 * @param codigo String código por el que buscar el ejemplar
	 * @return Ejemplar entidad coincidente
	 */
	public Ejemplar findByCodigo(String codigo);

	/**
	 * Devuelve un ejemplar que no esté en prestamo actualmente.
	 * 
	 * @param idRecurso Long indice del recurso cuyo ejemplar queremos
	 * @param fecha Date fecha de verificación del prestamo
	 * @return Ejemplar ejemplar que no está en prestamo
	 */
	@Query(nativeQuery = true, value=
			"SELECT ejemplar.* "
			+ " FROM ejemplar JOIN prestamo USING (id_ejemplar) "
			+ " WHERE ejemplar.id_recurso = :idRecurso AND prestamo.fecha_entrega IS NOT NULL "
			+ "     AND prestamo.fecha_entrega < :date AND LIMIT 1")
	public Ejemplar findFirstFree(Long idRecurso, Date fecha);

//	/**
//	 * Recupera todos los ejemplares que pertenezcan al Recurso del id dado.
//	 * 
//	 * @param idRecurso Long id del recurso para el que buscar los ejemplares.
//	 * @return List<Ejemplar> entidades coincientes
//	 */
//	public List<Ejemplar> findByRecurso_idRecurso(Long idRecurso);
	
}
