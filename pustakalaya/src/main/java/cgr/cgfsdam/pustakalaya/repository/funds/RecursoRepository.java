package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.repository.funds.specifications.RecursoSpecifications;

/**
 * Interfaz del repositorio de la entidad Recurso
 *
 * @author CGR-Casa
 */

@Repository("recursoRepository")
public interface RecursoRepository extends JpaRepository<Recurso, Long>, JpaSpecificationExecutor<Recurso> {
	
	/**
	 * Busca por el título exacto del recurso.
	 * 
	 * @param titulo String titulo.
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByTitulo(String titulo);
	
	/**
	 * Busca todos los recurso cuyo título contenga el texto dado
	 * @param titulo String texto a buscar en el título
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByTituloContainingIgnoreCase(String titulo);
	
	/**
	 * Busca un recurso por su ISBN
	 * @param isbn String ISBN a buscar
	 * @return Recurso entidad coincidente.
	 */
	Recurso findByIsbn(String isbn);
	
	/**
	 * Busca los recursos en los que esté incluido el autor dado.
	 * 
	 * @param autor Autor autor a buscar en los recursos
	 * @return List<Recurso> entidades coincidentes
	 */
	@Query("select r from Recurso r where :autor member of r.autores")
	List<Recurso> findByAutor(@Param("autor")Autor autor);
	
	/**
	 * Busca todos los recursos que coincidan con los datos del formulario dados.
	 * Solo tendrá en cuenta los valores
	 * 
	 * @param titulo String texto a buscar
	 * @param isbn
	 * @param autor
	 * @param genero
	 * @param idioma
	 * @param desde
	 * @param hasta
	 * @return
	 */
	default List<Recurso> findByFormData(String titulo, String isbn, Autor autor, Genero genero,
			Idioma idioma, Date desde, Date hasta) {
		return findAll(RecursoSpecifications.findByFormData(titulo, isbn, autor, genero, idioma, desde, hasta));
	}
	
	
}
