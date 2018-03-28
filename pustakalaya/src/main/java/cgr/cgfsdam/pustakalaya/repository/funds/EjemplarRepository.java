package cgr.cgfsdam.pustakalaya.repository.funds;

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
	
}
