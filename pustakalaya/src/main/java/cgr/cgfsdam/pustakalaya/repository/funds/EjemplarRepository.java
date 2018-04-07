package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;

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

//	/**
//	 * Recupera todos los ejemplares que pertenezcan al Recurso del id dado.
//	 * 
//	 * @param idRecurso Long id del recurso para el que buscar los ejemplares.
//	 * @return List<Ejemplar> entidades coincientes
//	 */
//	public List<Ejemplar> findByRecurso_idRecurso(Long idRecurso);
	
}
