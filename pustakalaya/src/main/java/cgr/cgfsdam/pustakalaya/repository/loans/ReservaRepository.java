package cgr.cgfsdam.pustakalaya.repository.loans;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.funds.specifications.RecursoSpecifications;
import cgr.cgfsdam.pustakalaya.repository.loans.specifications.ReservaSpecifications;

/**
 * Interface del repositorio de la entidad Reserva.
 *
 * @author CGR-Casa 
 */
@Repository("reservaRepository")
public interface ReservaRepository extends JpaRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {

	/**
	 * Devuelve un listado de reservas relacionadas con el recurso.
	 * 
	 * @param recurso Recurso entidad de busqueda
	 * @return List<Reserva> listado de entidades coincidentes
	 */
	List<Reserva> findByRecurso(Recurso recurso);

	/**
	 * Devuelve un listado de reservas relacionadas con el usuario.
	 * 
	 * @param usuario Usuario entidad de busqueda
	 * @return List<Reserva> listado de entidades coincidentes
	 */
	List<Reserva> findByUsuario(Usuario usuario);

	/**
	 * Devuelve una reserva dados un recurso, un usuario y un estado.
	 * 
	 * @param recurso
	 * @param usuario
	 * @param estadoReserva
	 * @return
	 */
	Reserva findByRecursoAndUsuarioAndEstadoReserva(Recurso recurso, Usuario usuario, EstadoReservaEnum estadoReserva);

	/**
	 * Busca reservas por los datos introducidos en el formulario.
	 * 
	 * @param estadoReserva
	 * @param idUsuario
	 * @param titulo
	 * @param isbn
	 * @return List<Reserva> conjunto de reservas que cumplen con los criterios de búsqueda.
	 */
	default List<Reserva> findByForm(EstadoReservaEnum estadoReserva, String idUsuario, String titulo, String isbn) {
		return findAll(ReservaSpecifications.findByFormData(estadoReserva, idUsuario, titulo, isbn));
	}
	
	/**
	 * Cuenta el número de reservas realizadas al recurso dado que se encuentran en el estado dado y que se han 
	 * realizado antes de la fecha dada.
	 * 
	 * @param recurso Recurso recurso asociado a las reservas a contar
	 * @param estadoReserva EstadoReservaEnum tipo de reservas a buscar
	 * @param fechaReserva Date fecha para la que contar las reservas previas
	 * @return Long numero de reservas que cumplen con los criterios.
	 */
	Long countByRecursoAndEstadoReservaAndFechaReservaBefore(Recurso recurso, EstadoReservaEnum estadoReserva,
			Date fechaReserva);
}
