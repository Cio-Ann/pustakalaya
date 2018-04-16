package cgr.cgfsdam.pustakalaya.service.funds;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Idioma;

/**
 * Interfaz del servicio de la entidad Idioma
 *
 * @author CGR-Casa
 */
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

	/**
	 * Elimina el idioma dado de base de datos.
	 * 
	 * @param idioma Idioma entidad a eliminar.
	 */
	void delete(Idioma idioma);

	/**
	 * Cuenta el número de recursos relacionados con un idioma dado.
	 * 
	 * @param idioma Idioma entidad por la que buscar.
	 * @return Long número de recursos con el idioma dado.
	 */
	Long countResourcesByIdioma(Idioma idioma);
}
