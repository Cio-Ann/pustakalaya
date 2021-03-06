package cgr.cgfsdam.pustakalaya.service.funds;

import java.util.Date;
import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;

/**
 * Interfaz del servicio de la entidad Recurso
 *
 * @author CGR-Casa
 */
public interface RecursoService {

	/**
	 * Busca por el título exacto del recurso.
	 * 
	 * @param titulo
	 *            String titulo.
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByTitulo(String titulo);

	/**
	 * Busca todos los recurso cuyo título contenga el texto dado
	 * 
	 * @param titulo
	 *            String texto a buscar en el título
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByTituloContainingIgnoreCase(String titulo);

	/**
	 * Busca un recurso por su ISBN
	 * 
	 * @param isbn
	 *            String ISBN a buscar
	 * @return Recurso entidad coincidente.
	 */
	Recurso findByIsbn(String isbn);

	/**
	 * Busca los recursos en los que esté incluido el autor dado.
	 * 
	 * @param autor
	 *            Autor autor a buscar en los recursos
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByAutor(Autor autor);

	/**
	 * Busca todos los recursos que coincidan con los datos del formulario dados.
	 * Solo tendrá en cuenta los valores
	 * 
	 * @param titulo
	 *            String texto a buscar
	 * @param isbn
	 * @param autor
	 * @param genero
	 * @param idioma
	 * @param desde
	 * @param hasta
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByFormData(String titulo, String isbn, Autor autor, Genero genero, Idioma idioma, Date desde,
			Date hasta);

	/**
	 * Busca un recurso por su id.
	 * 
	 * @param idRecurso Long id del recurso a buscar.
	 * @return Recurso entidad coincidente.
	 */
	Recurso findById(Long idRecurso);

	/**
	 * Guarda el recurso dado en la capa de persistencia.
	 * 
	 * @param recurso Recurso entidad a persistir
	 */
	void save(Recurso recurso);

	/**
	 * Elimina el recurso dado de la base de datos.
	 * 
	 * @param recurso Recurso entidad a eliminar.
	 */
	void delete(Recurso recurso);

	/**
	 * Recupera todos los recursos de base de datos.
	 * 
	 * @return List<Recurso> todos los recursos de bbdd
	 */
	List<Recurso> findAll();

}
