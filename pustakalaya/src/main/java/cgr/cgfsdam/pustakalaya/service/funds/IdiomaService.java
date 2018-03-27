package cgr.cgfsdam.pustakalaya.service.funds;

import cgr.cgfsdam.pustakalaya.model.funds.Idioma;

public interface IdiomaService {

	/**
	 * Busca un genero por su nombre exacto.
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
}
