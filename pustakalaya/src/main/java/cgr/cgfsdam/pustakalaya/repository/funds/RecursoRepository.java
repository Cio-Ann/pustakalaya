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
	 * 
	 * @param titulo String texto a buscar en el título
	 * @return List<Recurso> entidades coincidentes
	 */
	List<Recurso> findByTituloContainingIgnoreCase(String titulo);

	/**
	 * Busca un recurso por su ISBN
	 * 
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
	List<Recurso> findByAutor(@Param("autor") Autor autor);

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
	default List<Recurso> findByFormData(String titulo, String isbn, Autor autor, Genero genero, Idioma idioma,
			Date desde, Date hasta) {

		return findAll(RecursoSpecifications.findByFormData(titulo, isbn, autor, genero, idioma, desde, hasta));
	}

	/**
	 * Devuelve el número de ejemplares a disposición del publico que no se encuentran en prestamo en este momento.
	 * 
	 * @param recurso
	 * @return
	 */
//	@Query("SELECT "
//			+ "    COUNT(e) "
//			+ "FROM "
//			+ "    Recurso r "
//			+ "    JOIN r.ejemplares e "
//			+ "    LEFT JOIN Prestamo p ON p.ejemplar = e "
//			+ "WHERE "
//			+ "    p.fechaVencimiento > CURRENT_DATE "
//			+ "    and p.fechaEntrega IS NULL "
//			+ "    and r = :recurso ")
	@Query(nativeQuery= true, value=
			"SELECT " + 
			"	COUNT(*)" + 
			"FROM " + 
			"	recurso r" + 
			"	JOIN ejemplar e USING(id_recurso)" + 
			"	JOIN prestamo p USING (id_ejemplar)" + 
			"WHERE " + 
			"	r.id_recurso = :idRecurso" + 
			"	AND p.fecha_entrega IS NULL")
	long countEjemplaresPrestados(@Param("idRecurso") Long idRecurso);

	/**
	 * Devuelve el número de reservas activas para el recurso dado.
	 * 
	 * @param recurso Recurso
	 * @return Long número de reservas activas
	 */
	@Query("select count(b) from Reserva b join b.recurso r where r = :recurso and b.estadoReserva = 1")
	Long countReservasPendientes(@Param("recurso") Recurso recurso);

	/**
	 * Devuelve la fecha de la proxima devolución prevista de un ejemplar del recurso dado.
	 * Utiliza nativeQuery
	 * 
	 * @param recurso Recurso entidad a evaluar
	 * @return Date fecha de la proxima devolución
	 */
	@Query(nativeQuery = true, value = "SELECT MIN(p.fecha_vencimiento) FROM ejemplar e "
			+ "JOIN prestamo p USING (id_ejemplar) WHERE e.id_recurso = :idRecurso AND p.fecha_entrega IS NULL "
			+ "GROUP BY e.id_recurso ")
	Date getProximaDevolucion(@Param("idRecurso") Long idRecurso);
}
