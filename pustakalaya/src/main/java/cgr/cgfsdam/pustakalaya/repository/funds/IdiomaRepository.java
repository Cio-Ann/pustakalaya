package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	 * @param nombre String Nombre a buscar
	 * @return Idioma entidad encontrada
	 */
	Idioma findByNombreIgnoreCase(String nombre);
	
}
