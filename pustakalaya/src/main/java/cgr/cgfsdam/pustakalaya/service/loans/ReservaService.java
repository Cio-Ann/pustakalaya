package cgr.cgfsdam.pustakalaya.service.loans;

import java.util.Date;
import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.funds.specifications.RecursoSpecifications;

/**
 * Interfaz del servicio de la entidad Reserva.
 *
 * @author CGR-Casa
 */
public interface ReservaService {

	/**
	 * Recupera una entidad a partir de su id
	 * 
	 * @param idReserva Long id de la reserva
	 * @return Reserva entidad buscada
	 */
	Reserva find(Long idReserva);

	/**
	 * Lista todas las entidades.
	 * 
	 * @return List<Reserva> todas las reservas
	 */
	List<Reserva> findAll();

	/**
	 * Lista todas las entidades relacionada con un recurso.
	 * 
	 * @param recurso Recurso entidad por la que buscar.
	 * @return List<Reserva> todas las reservas relacionadas.
	 */
	List<Reserva> findByRecurso(Recurso recurso);

	/**
	 * Lista  todas las entidades relacionada con un usuario.
	 * 
	 * @param usuario Usuario entidad por la que buscar.
	 * @return List<Reserva> todas las reservas relacionadas.
	 */
	List<Reserva> findByUsuario(Usuario usuario);

	/**
	 * Busca una reserva por recurso, usuario y estado.
	 * 
	 * @param recurso Recurso entidad recurso de busqueda
	 * @param usuario Usuario entidad autor de búsqueda
	 * @param estadoReserva EstadoReservaEnum valor de estado de busqueda
	 * @return Reserva entidad coincidente. 
	 */
	Reserva findByRecursoAndUsuarioAndEstadoReserva(Recurso recurso, Usuario usuario, EstadoReservaEnum estadoReserva);

	/**
	 * Guarda una reserva.
	 * 
	 * @param reserva
	 */
	void save(Reserva reserva);

	/**
	 * Elimina una reserva.
	 * 
	 * @param reserva
	 */
	void delete(Reserva reserva);

	/**
	 * Busca reservas por los datos introducidos en el formulario.
	 * 
	 * @param estadoReserva
	 * @param idUsuario
	 * @param titulo
	 * @param isbn
	 * @return List<Reserva> conjunto de reservas que cumplen los criterios de búsqueda
	 */
	List<Reserva> findByForm(EstadoReservaEnum estadoReserva, String idUsuario, String titulo, String isbn);

	/**
	 * Cuenta el número de reservas realizadas al recurso dado que se encuentran en el estado dado y que se han 
	 * realizado antes de la fecha dada.
	 * 
	 * @param recurso Recurso recurso asociado a las reservas a contar
	 * @param estadoReserva EstadoReservaEnum tipo de reservas a buscar
	 * @param fechaReserva Date fecha para la que contar las reservas previas
	 * @return Long numero de reservas que cumplen con los criterios.
	 */
	Long countByRecursoAndEstadoReservaAndFechaReservaBefore(Recurso recurso, EstadoReservaEnum estadoReserva, Date fechaReserva);
	
}
