package cgr.cgfsdam.pustakalaya.service.funds;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Idioma;

public interface IdiomaService {

	/**
	 * Busca un idioma por su nombre exacto.
	 * 
	 * @param nombre
	 *            String nombre del idioma a buscar
	 * @return Idioma entidad coincidente
	 */
	Idioma findByNombreIgnoreCase(String nombre);

	/**
	 * Guarda el idioma en base de datos.
	 * 
	 * @param idioma
	 *            Idioma objeto a guardar
	 */
	void save(Idioma idioma);

	/**
	 * Recupera todos los idiomas de la capa de persistencia.
	 * 
	 * @return List<Idioma> idiomas de base de datos.
	 */
	List<Idioma> findAll();
}
